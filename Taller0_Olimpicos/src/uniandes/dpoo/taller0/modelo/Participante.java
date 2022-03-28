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
	 * > Añadir al UML el método añadirRegistroActividad.
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
	
}
