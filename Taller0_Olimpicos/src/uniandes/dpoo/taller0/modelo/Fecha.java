package uniandes.dpoo.taller0.modelo;

/*
 * Esta clase encapsula toda la información de una fecha.
 */
public class Fecha {
	
	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > Considerar borrar el segundo constructor.
	 */
	
	
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El entero que indica el día.
	 */
	private int dia;

	/**
	 * El entero que indica el mes.
	 */
	private int mes;

	/**
	 * El entero que indica el año.
	 */
	private int año;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye una fecha e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param dia
	 * @param mes
	 * @param año
	 */
	public Fecha(int dia, int mes, int año) {
		this.dia = dia;
		this.mes = mes;
		this.año = año;
	}
	
	/**
	 * Construye una fecha e inicializa sus atributos con la información del
	 * parámetro, el cual es la fecha representada como un caracter.
	 * 
	 * @param fechaString
	 */
	public Fecha(String fechaString) {
		this.dia = Integer.parseInt(fechaString.substring(0, 2));
		this.mes = Integer.parseInt(fechaString.substring(3, 5));
		this.año = Integer.parseInt(fechaString.substring(6, 8));
	}

	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el día.
	 * 
	 * @return dia
	 */
	public int getDia() {
		return dia;
	}
	
	/**
	 * Consulta el mes.
	 * 
	 * @return mes
	 */
	public int getMes() {
		return mes;
	}
	
	/**
	 * Consulta el año.
	 * 
	 * @return año
	 */
	public int getAño() {
		return año;
	}
	
}
