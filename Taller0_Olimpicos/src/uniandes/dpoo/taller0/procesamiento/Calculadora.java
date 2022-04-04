package uniandes.dpoo.taller0.procesamiento;

import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.taller0.modelo.Actividad;
import uniandes.dpoo.taller0.modelo.Hora;
import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.modelo.Proyecto;
import uniandes.dpoo.taller0.modelo.RegistroActividad;

public class Calculadora {
	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************
	
	/**
	 * Calcula y notifica el tiempo total invertido por un participante.
	 * 
	 * @param participante
	 * @param proyecto
	 */
	public void tiempoTotalInvertido(Participante participante, Proyecto proyecto) {
		ArrayList<RegistroActividad> registros = participante.getRegistros();
		HashMap<String, Actividad> actividades = proyecto.getActividades();
		
		int horas = 0;
		int minutos = 0;
		int segundos = 0;
		
		for (RegistroActividad registro:registros) {
			String titulo = registro.getNombre();
			if (actividades.get(titulo) == null);
			
			else {
				Hora horaInicio = actividades.get(titulo).getHoraInicio();
				Hora horaFin = actividades.get(titulo).getHoraFin();
				Hora duracion = calcularDuracion(horaInicio, horaFin);
				horas += duracion.getHora();
				minutos += duracion.getMinutos();
				segundos += duracion.getSegundos();
			}
		}
		
		System.out.println("\nEl tiempo total que invirtió el participante fue:");
		System.out.println(" -Horas:" + horas + ".");
		System.out.println(" -Minutos:" + minutos + ".");
		System.out.println(" -Segundos:" + segundos + ".");
	}
	
	/**
	 * Calcula la diferencia enrte dos horas.
	 * @param hora1
	 * @param hora2
	 * 
	 * @return Diferencia horas.
	 */
	private Hora calcularDuracion(Hora hora1, Hora hora2) {
		// Horas.
		int diferenciahoras;
		if (hora1.getHora() > hora2.getHora()) {
			diferenciahoras = hora1.getHora() - hora2.getHora();
		}
		else {
			diferenciahoras = hora2.getHora() - hora1.getHora();
		}
		
		// Minutos.
		int diferenciaMinutos;
		if (hora1.getMinutos() > hora2.getMinutos()) {
			diferenciaMinutos = hora1.getMinutos() - hora2.getMinutos();
		}
		else {
			diferenciaMinutos = hora2.getMinutos() - hora1.getMinutos();
		}
		
		// Segundos.
		int diferenciaSegundos;
		if (hora1.getSegundos() > hora2.getSegundos()) {
			diferenciaSegundos = hora1.getSegundos() - hora2.getSegundos();
		}
		else {
			diferenciaSegundos = hora2.getSegundos() - hora1.getSegundos();
		}
		
		// Conversión minutos sobrantes a horas sobrantes si las primeras son 
		// mayores que 60.
		if (diferenciaMinutos >= 60) {
			diferenciahoras += diferenciaMinutos / 60;
			diferenciaMinutos -= 60 * (diferenciaMinutos / 60);
		}

		// Conversión segundos sobrantes a minutos sobrantes si los primeras 
		// son mayores que 60.
		if (diferenciaSegundos >= 60) {
			diferenciaMinutos += diferenciaSegundos / 60;
			diferenciaSegundos -= 60 * (diferenciaSegundos / 60);
		}
		
		Hora diferencia = new Hora(diferenciahoras, diferenciaMinutos, diferenciaSegundos);
		return diferencia;
	}
	
}
