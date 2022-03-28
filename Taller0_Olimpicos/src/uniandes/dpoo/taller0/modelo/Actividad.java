package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;

/*
 * Esta clase encapsula toda la información de una actividad.
 */
public class Actividad {

	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > Añadir el constructor de la actividad al UML.
	 * > Añadir el método añadirRegistro al UML.
	 * > El atributo registro cambió a registros.
	 */


	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El título de la actividad.
	 */
	private String titulo;
	
	/**
	 * La descripción de la actividad.
	 */
	private String descripcion;
	
	/**
	 * El tipo de la actividad.
	 */
	private String tipo;
	
	/**
	 * La fecha de realización de la actividad.
	 */
	private Fecha fechaRealizacion;
	
	/**
	 * La hora de inicio de la actividad.
	 */
	private Hora horaInicio;
	
	/**
	 * La hora de finalización de la actividad.
	 */
	private Hora horaFin;
	
	/**
	 * La lista de registros de la actividad.
	 */
	private ArrayList<RegistroActividad> registros;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye una actividad e inicializa sus atributos con la información 
	 * de los parámetros.
	 * 
	 * @param titulo
	 * @param descripcion
	 * @param tipo
	 * @param fechaRealizacion
	 * @param horaInicio
	 * @param horaFin
	 */
	public Actividad(String titulo, String descripcion, String tipo, Fecha fechaRealizacion, Hora horaInicio, Hora horaFin) {
		this.titulo = titulo;
		this.descripcion = descripcion;
		this.tipo = tipo;
		this.fechaRealizacion = fechaRealizacion;
		this.horaInicio = horaInicio;
		this.horaFin = horaFin;
	}
	

	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************
	
	/**
	 * Consulta el título.
	 * 
	 * @return titulo
	 */
	public String getTitulo() {
		return titulo;
	}

	/**
	 * Consulta la descripción.
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}
	
	/**
	 * Consulta el tipo.
	 * 
	 * @return tipo
	 */
	public String getTipo() {
		return tipo;
	}
	
	/**
	 * Consulta la fechaRealizacion.
	 * 
	 * @return fechaRealizacion
	 */
	public Fecha getFechaRealizacion() {
		return fechaRealizacion;
	}
	
	/**
	 * Consulta la hora de inicio.
	 * 
	 * @return horaInicio
	 */
	public Hora getHoraInicio() {
		return horaInicio;
	}
	
	/**
	 * Consulta la hora de finalización.
	 * 
	 * @return horaFin
	 */
	public Hora getHoraFin() {
		return horaFin;
	}
	
	/**
	 * Consulta la lista de registros de la actividad.
	 * 
	 * @return registros
	 */
	public ArrayList<RegistroActividad> getRegistros() {
		return registros;
	}
	
	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Añade un registro a la lista de registros.
	 * 
	 * @param registro
	 */
	public void añadirRegistro(RegistroActividad registro) {
		registros.add(registro);
	}
	
}
