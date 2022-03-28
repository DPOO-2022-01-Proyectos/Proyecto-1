package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;

/*
 * Esta clase encapsula toda la información de un proyecto.
 */
public class Proyecto {

	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > Atributo nuevosParticipantes cambió a participantes.
	 * > Añadir métodos añadirActividad y añadirParticipante al UML.
	 */


	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del proyecto.
	 */
	private String nombre;
	
	/**
	 * La descripción del proyecto.
	 */
	private String descripcion;
	
	/**
	 * La fecha de inicio del proyecto.
	 */
	private Fecha fechaInicio;
	
	/**
	 * La fecha de finalización aproximada del proyecto.
	 */
	private Fecha fechaFin;
	
	/**
	 * El participante inicial del proyecto.
	 */
	private Participante participanteInicial;
	
	/**
	 * La lista de participantes del proyecto.
	 */
	private ArrayList<Participante> participantes;
	
	/**
	 * La lista de actividades del proyecto.
	 */
	private ArrayList<Actividad> actividades;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un proyecto e inicializa sus atributos con la información de
	 * los parámetros.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param fechaInicio
	 * @param fechaFin
	 * @param participanteInicial
	 */
	public Proyecto(String nombre, String descripcion, Fecha fechaInicio, Fecha fechaFin, Participante participanteInicial) {
		this.nombre = nombre;
		this.descripcion = descripcion;
		this.fechaInicio = fechaInicio;
		this.fechaFin = fechaFin;
		this.participanteInicial = participanteInicial;
	}


	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del proyecto.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}

	/**
	 * Consulta la descripción del proyecto.
	 * 
	 * @return descripcion
	 */
	public String getDescripcion() {
		return descripcion;
	}

	/**
	 * Consulta la fecha de inicio del proyecto.
	 * 
	 * @return fechaInicio
	 */
	public Fecha getFechaInicio() {
		return fechaInicio;
	}

	/**
	 * Consulta la fecha de finalización del proyecto.
	 * 
	 * @return fechaFin
	 */
	public Fecha getFechaFin() {
		return fechaFin;
	}

	/**
	 * Consulta el participante inicial del proyecto.
	 * 
	 * @return participanteInicial
	 */
	public Participante getParticipanteInicial() {
		return participanteInicial;
	}

	/**
	 * Consulta la lista de participantes del proyecto.
	 * 
	 * @return participantes
	 */
	public ArrayList<Participante> getParticipantes() {
		return participantes;
	}

	/**
	 * Consulta la lista de actividades del proyecto.
	 * 
	 * @return actividades
	 */
	public ArrayList<Actividad> getActividades() {
		return actividades;
	}
	
	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************

	/**
	 * Añade un participante a la lista de participantes.
	 * 
	 * @param participante
	 */
	public void añadirParticipante(Participante participante) {
		participantes.add(participante);
	}
	
	/**
	 * Añade una actividad a la lista de actividades.
	 * 
	 * @param actividad
	 */
	public void añadirActividad(Actividad actividad) {
		actividades.add(actividad);
	}
	
}
