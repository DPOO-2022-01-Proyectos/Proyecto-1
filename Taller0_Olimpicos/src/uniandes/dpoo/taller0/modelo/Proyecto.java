package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;
import java.util.HashMap;

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
	 * El mapa de participantes del proyecto.
	 */
	private HashMap<String, Participante> participantes;
	
	/**
	 * El mapa de actividades del proyecto.
	 */
	private HashMap<String,Actividad> actividades;
	
	
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
		this.participantes = new HashMap<String, Participante>();
		this.actividades = new HashMap<String,Actividad>();
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
	 * Consulta el mapa de participantes del proyecto.
	 * 
	 * @return participantes
	 */
	public HashMap<String, Participante> getParticipantes() {
		return participantes;
	}

	/**
	 * Consulta el mapa de actividades del proyecto.
	 * 
	 * @return actividades
	 */
	public HashMap<String,Actividad> getActividades() {
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
		participantes.put(participante.getCorreo(), participante);
	}
	
	/**
	 * Añade una actividad al mapa de actividades.
	 * 
	 * @param actividad
	 */
	public void añadirActividad(Actividad actividad) {
		actividades.put(actividad.getTitulo(), actividad);
	}
	
}
