package uniandes.dpoo.taller0.procesamiento;

import java.util.HashMap;

import uniandes.dpoo.taller0.modelo.Actividad;
import uniandes.dpoo.taller0.modelo.Fecha;
import uniandes.dpoo.taller0.modelo.Hora;
import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.modelo.Proyecto;
import uniandes.dpoo.taller0.modelo.RegistroActividad;

/*
 * Esta clase se encarga de manejar todos los procedimientos relacionados a 
 * la manipulación de los proyectos.
 */
public class AdministradorDeProyectos {

	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > Considerar crear la clase Loader.
	 * > Atributo miembroActual cambió a participanteActual.
	 * > Añadir el atributo del cronómetro.
	 * > Validar el nombre del proyecto antes de usar el método crearProyecto.
	 * > Cambiar la signatura del método crearProyecto en el UML.
	 * > Cambiar UML métodos registrarActividad y registrarActividadExistente.
	 * > Añadir estos métodos al UML:
	 * 	- AdministradorDeProyectos (constructor).
	 * 	- getParticipanteActual.
	 * 	- getProyectoActual.
	 *  - getProyectos.
	 *  - iniciarSesion.
	 *  - abrirProyecto.
	 *  - añadirProyecto.
	 *  - esNombreValido.
	 */


	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El participante que actualmente está usando la aplicación.
	 */
	private Participante participanteActual;
	
	/**
	 * El proyecto que actualmente se está manipulando.
	 */
	private Proyecto proyectoActual;
	
	/**
	 * Mapa que guarda los proyectos por nombre.
	 */
	private HashMap<String, Proyecto> proyectos;
	
	/**
	 * Mapa que guarda los participantes.
	 */
	private HashMap<String, Participante> participantes;
	
	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye un administrador de proyectos e inicializa sus atributos vacíos.
	 */
	public AdministradorDeProyectos() {
		this.proyectos = new HashMap<String, Proyecto>();
		this.participantes = new HashMap<String, Participante>();
	}
	
	
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el participante actual.
	 * 
	 * @return participanteActual
	 */
	public Participante getParticipanteActual() {
		return participanteActual;
	}
	
	/**
	 * Consulta el proyecto actual.
	 * 
	 * @return proyectoActual
	 */
	public Proyecto getProyectoActual() {
		return proyectoActual;
	}
	
	/**
	 * Consulta el mapa de los proyectos.
	 * 
	 * @return proyectos
	 */
	public HashMap<String, Proyecto> getProyectos() {
		return proyectos;
	}
	
	/**
	 * Consulta el mapa de los participantes.
	 * 
	 * @return participantes
	 */
	public HashMap<String, Participante> getParticipantes() {
		return participantes;
	}
	
	
	// ************************************************************************
	// Métodos de los participantes
	// ************************************************************************
	
	/**
	 * Asigna un objeto de tipo Participante al atributo participanteActual.
	 * 
	 * @param participante
	 */
	public void iniciarSesion(Participante participante) {
		participanteActual = participante;
	}
	
	/**
	 * Crea y añade al proyecto actual un nuevo participante.
	 * 
	 * @param nombre
	 * @param correo
	 */
	public void añadirParticipante(String nombre, String correo) {
		Participante nuevoParticipante = new Participante(nombre, correo);
		nuevoParticipante.añadirProyecto(proyectoActual.getNombre());
		proyectoActual.añadirParticipante(nuevoParticipante);
		añadirParticipanteAMapa(nuevoParticipante);
	}
	
	/**
	 * Añade un participante a la tabla de Hash de participantes.
	 * 
	 * @param participante
	 */
	public void añadirParticipanteAMapa(Participante participante) {
		participantes.put(participante.getCorreo(), participante);
	}
	
	
	// ************************************************************************
	// Métodos de los proyectos
	// ************************************************************************
	
	/**
	 * Busca en el mapa proyectos un proyecto por su nombre y se lo asigna al
	 * atributo proyectoActual. Este método asume que el proyecto existe.
	 * 
	 * @param nombreProyecto
	 */
	public void abrirProyecto(String nombreProyecto) {
		proyectoActual = proyectos.get(nombreProyecto);
	}
	
	/**
	 * Añade un proyecto al mapa de proyectos. Este método asume que tanto el
	 * proyecto como su nombre son válidos para añadir.
	 * 
	 * @param proyectoAAñadir
	 */
	public void añadirProyecto(Proyecto proyectoAAñadir) {
		proyectos.put(proyectoAAñadir.getNombre(), proyectoAAñadir);
	}
	
	/**
	 * Crea un nuevo proyecto con la información de los parámetros y lo guarda
	 * en el mapa proyectos. Este método asume que todos los parámetros ingresados
	 * son válidos, y que el nombre del proyecto aún no existe.
	 * 
	 * @param nombre
	 * @param descripcion
	 * @param cadenaFechaInicio
	 * @param cadenaFechaFin
	 */
	public void crearNuevoProyecto(String nombre, String descripcion, Fecha fechaInicio, Fecha fechaFin) {
		Proyecto nuevoProyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, participanteActual);
		nuevoProyecto.añadirParticipante(participanteActual);
		participanteActual.añadirProyecto(nombre);
		añadirProyecto(nuevoProyecto);
	}
	
	 /**
	  * Permite verificar si el nombre de un proyecto que se desea añadir es
	  * válido, es decir, si la llave no existe aún en el mapa. También permite
	  * determinar si un proyecto existe dentro del mapa.
	  * 
	  * @param nombreProyecto El nombre del proyecto que se quiere añadir.
	  * 
	  * @return True si el nombre es válido, False de lo contrario.
	  */
	public boolean esNombreValido(String nombreProyecto) {
		boolean esValido = true;
		if (proyectos.containsKey(nombreProyecto)) {
			esValido = false;
		}
		return esValido;
	}
	
	
	// ************************************************************************
	// Métodos de las actividades
	// ************************************************************************
	
	/**
	 * Añade un nuevo registro de una actividad que ya existe.
	 * 
	 * @param actividad
	 * @param participante
	 * @param cadenaFecha
	 * @param cadenaHora
	 */
	public void registrarActividad(Actividad actividad, Participante participante, Fecha fecha, Hora hora) {
		RegistroActividad nuevoRegistro = new RegistroActividad(actividad.getTitulo(), participante, fecha, hora);
		actividad.añadirRegistro(nuevoRegistro);
		participanteActual.añadirRegistroActividad(nuevoRegistro);
	}
	
}
