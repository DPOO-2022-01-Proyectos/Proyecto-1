package uniandes.dpoo.taller0.modelo;

/*
 * Esta clase encapsula toda la información de una hora.
 */
public class Hora {

	// ************************************************************************
	// Pendientes
	// ************************************************************************
	
	/*
	 * > Cambiar el UML de la clase Hora.
	 * > Considerar borrar el segundo constructor.
	 */
	
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El entero que indica la hora.
	 */
	private int hora;

	/**
	 * El entero que indica los minutos.
	 */
	private int minutos;
	
	/**
	 * El entero que indica los segundos.
	 */
	private int segundos;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************
	
	/**
	 * Construye una hora e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param hora
	 * @param minutos
	 * @param segundos
	 */
	public Hora(int hora, int minutos, int segundos) {
		this.hora = hora;
		this.minutos = minutos;
		this.segundos = segundos;
	}
	
	/**
	 * Construye una hora e inicializa sus atributos con la información del
	 * parámetro, el cual es la hora representada como un caracter.
	 * 
	 * @param horaString
	 */
	public Hora(String horaString) {
		this.hora = Integer.parseInt(horaString.substring(0, 2));
		this.minutos = Integer.parseInt(horaString.substring(3, 5));
		this.segundos = Integer.parseInt(horaString.substring(6, 8));
	}
	
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************
	
	/**
	 * Consulta la hora.
	 * 
	 * @return hora
	 */
	public int getHora() {
		return hora;
	}
	
	/**
	 * Consulta los minutos.
	 * 
	 * @return minutos
	 */
	public int getMinutos() {
		return minutos;
	}
	
	/**
	 * Consulta los segundos.
	 * 
	 * @return segundos
	 */
	public int getSegundos() {
		return segundos;
	}
	
}
