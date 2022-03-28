package uniandes.dpoo.taller0.procesamiento;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;

import uniandes.dpoo.taller0.modelo.Hora;
import uniandes.dpoo.taller0.modelo.Participante;

/*
 * Esta clase encapsula la información del cronómetro.
 */
public class Cronometro {

	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > Cambiar el UML de esta clase.
	 * > La clase Aplicacion es la que debe tener un cronómetron no
	 * 	 AdministradorDeProyectos.
	 * 
	 */


	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * Lista de listas que guarda las horas registradas.
	 * Cada elemento es un arreglo de dos componenetes, y representan un intervalo
	 * de tiempo. Esto facilitará el cálculo del tiempo total.
	 */
	private ArrayList<ArrayList<Hora>> registroHoras;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un cronómetro e inicializa la lista de listas vacía.
	 */
	public Cronometro() {
		this.registroHoras = new ArrayList<ArrayList<Hora>>();
	}
	
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el registro de horas.
	 * 
	 * @return registroHoras
	 */
	public ArrayList<ArrayList<Hora>> getRegistroHoras() {
		return registroHoras;
	}
	
	
	// ************************************************************************
	// Métodos para manipular el cronómetro
	// ************************************************************************

	/**
	 * Inicia el cronómetro. Crea el primer arreglo (intervalo) asignando a
	 * su componenete izquierdo (índice 0) la hora actual. 
	 */
	public void inciar() {
		ArrayList<Hora> primerIntervalo = new ArrayList<Hora>();
        primerIntervalo.add(darHoraActual());
		registroHoras.add(primerIntervalo);
	}
	
	/**
	 * Pausa el cronómetro. Lo hace asignando la hora actual al segundo componente
	 * (índice 1) al último intervalo de registroHoras.
	 */
	public void pausar() {
		if (registroHoras.size() == 1) {
			ArrayList<Hora> primerIntervalo = registroHoras.get(0);
			primerIntervalo.add(1, darHoraActual());
		}
		else {
			ArrayList<Hora> ultimoIntervalo = registroHoras.get(registroHoras.size() - 1);
			ultimoIntervalo.add(1, darHoraActual());
		}
	}
	
	/**
	 * Continúa el cronómetro. Lo hace creando un nuevo intervalo y asignando
	 * a su primer componente (índice 0) la hora actual.
	 */
	public void continuar() {
		ArrayList<Hora> nuevoIntervalo = new ArrayList<Hora>();
		nuevoIntervalo.add(0, darHoraActual());
		registroHoras.add(nuevoIntervalo);		
	}	
	
	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Calcula la duración total neta de la actividad usando los registros de
	 * registroHoras.
	 * 
	 * @return Duración en formato HH:mm:ss.
	 */
	public Hora calcularDuracion() {
		Hora primeraHora = registroHoras.get(0).get(0);
		Hora ultimaHora = registroHoras.get(registroHoras.size() - 1).get(1);
		
		// Cálculo horas, minutos y segundos totales.
		int horasTotales = ultimaHora.getHora() - primeraHora.getHora();
		int minutosTotales = calcularDiferenciaMinutos(primeraHora, ultimaHora);
		int segundosTotales = calcularDiferenciaSegundos(primeraHora, ultimaHora);
		
		// Cálculo minutos totales que no se deben considerar.
		int minutosSobrantes = 0;
		for (ArrayList<Hora> intervalo:registroHoras) {
			Hora hora1 = intervalo.get(0);
			Hora hora2 = intervalo.get(1);
			minutosSobrantes += calcularDiferenciaMinutos(hora1, hora2);
		}
		
		// Cálculo segundos totales que no se deben considerar.
		int segundosSobrantes = 0;
		for (ArrayList<Hora> intervalo:registroHoras) {
			Hora hora1 = intervalo.get(0);
			Hora hora2 = intervalo.get(1);
			segundosSobrantes += calcularDiferenciaSegundos(hora1, hora2);
		}
		
		// Conversión minutos sobrantes a horas sobrantes si las primeras son 
		// mayores que 60.
		int horasSobrantes = 0;
		if (minutosSobrantes >= 60) {
			horasSobrantes += minutosSobrantes / 60;
			minutosSobrantes -= 60 * (minutosSobrantes / 60);
		}
		
		// Conversión segundos sobrantes a minutos sobrantes si los primeras 
		// son mayores que 60.
		if (segundosSobrantes >= 60) {
			minutosSobrantes += segundosSobrantes / 60;
			segundosSobrantes -= 60 * (segundosSobrantes / 60);
		}
		
		// Cálculo duración neta actividad.
		horasTotales -= horasSobrantes;
		minutosTotales -= minutosSobrantes;
		segundosTotales -= segundosSobrantes;
		Hora duracionTotal = new Hora(horasTotales, minutosTotales, segundosTotales);
		return duracionTotal;
	}
	
	/**
	 * Calcula y retorna la hora actual expresada como un objeto de tipo Hora.
	 * 
	 * @return La hora actual.
	 */
	private Hora darHoraActual () {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		Hora horaActual = new Hora(dtf.format(LocalDateTime.now()).substring(11, 19));
		return horaActual;
	}
	
	/**
	 * Calcula la diferencia de los minutos de dos horas dadas.
	 * 
	 * @param hora1
	 * @param hora2
	 * 
	 * @return Diferencia de los minutos.
	 */
	private int calcularDiferenciaMinutos (Hora hora1, Hora hora2) {
		int diferencia;
		if (hora1.getMinutos() > hora2.getMinutos()) {
			diferencia = hora1.getMinutos() - hora2.getMinutos();
		}
		else {
			diferencia = hora2.getMinutos() - hora1.getMinutos();
		}
		return diferencia;
	}
	
	/**
	 * Calcula la diferencia de los segundos de dos horas dadas.
	 * 
	 * @param hora1
	 * @param hora2
	 * 
	 * @return Diferencia de los segundos.
	 */
	private int calcularDiferenciaSegundos (Hora hora1, Hora hora2) {
		int diferencia;
		if (hora1.getSegundos() > hora2.getSegundos()) {
			diferencia = hora1.getSegundos() - hora2.getSegundos();
		}
		else {
			diferencia = hora2.getSegundos() - hora1.getSegundos();
		}
		return diferencia;
	}
	
}
