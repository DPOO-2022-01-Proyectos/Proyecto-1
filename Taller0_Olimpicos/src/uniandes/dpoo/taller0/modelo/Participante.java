package uniandes.dpoo.taller0.modelo;

import java.util.ArrayList;

/*
 * Esta clase encapsula toda la información de un participante.
 */
public class Participante {

	// ************************************************************************
	// Pendientes
	// ************************************************************************
	
	/*
	 * > Quitar del UML el atributo esParticipanteInicial; debería tenerlo el Proyecto.
	 * > Cambiar el sentido de la flecha "autor".
	 * > El atributo registroActividParticipante es cambió por registros.
	 * > Añadir al UML el método añadirRegistroActividad, proyectos y añadirProyecto.
	 */

	
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del participante.
	 */
	private String nombre;
	
	/**
	 * El correo del participante.
	 */
	private String correo;
	
	/**
	 * La lista de los registros hechas por el participante.
	 */
	private ArrayList<RegistroActividad> registros;
	
	/**
	 * La lista de los nombres de los proyectos de los que hace parte el
	 * participante.
	 */
	private ArrayList<String> proyectos;

	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un participante e inicializa sus atributos con la información
	 * de los parámetros.
	 * 
	 * @param nombre
	 * @param correo
	 */
	public Participante(String nombre, String correo) {
		this.nombre = nombre;
		this.correo = correo;
		this.registros = new ArrayList<RegistroActividad>();
		this.proyectos = new ArrayList<String>();
	}
	
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del participante.
	 * 
	 * @return nombre
	 */	
	public String getNombre() {
		return nombre;
	}

	/**
	 * Consulta el correo del participante.
	 * 
	 * @return correo
	 */	
	public String getCorreo() {
		return correo;
	}

	/**
	 * Consulta la lista de registros del participante.
	 * 
	 * @return registros
	 */	
	public ArrayList<RegistroActividad> getRegistros() {
		return registros;
	}
	
	/**
	 * Consulta la lista de proyectos del participante.
	 * 
	 * @return proyectos
	 */	
	public ArrayList<String> getProyectos() {
		return proyectos;
	}
	
	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************
	
	/**
	 * Añade un registro de una actividad realizada por el participante a
	 * la lista de registros.
	 * 
	 * @param registro
	 */
	public void añadirRegistroActividad(RegistroActividad registro) {
		registros.add(registro);
	}
	
	/**
	 * Añade un proyecto a la lista de proyectos.
	 * 
	 * @param nombreProyecto
	 */
	public void añadirProyecto(String nombreProyecto) {
		proyectos.add(nombreProyecto);
	}
	
}
