package uniandes.dpoo.taller0.modelo;

/*
 * Esta clase encapsula toda la información del registro de una actividad.
 */
public class RegistroActividad {

	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > Quitar la flecha actividad del UML.
	 * > Atributo nombreRegistro cambió a nombre.
	 * > Añadir al UML el atributo fehca y su getter.
	 * > Añadir al UML el atributo hora y su getter.
	 */
	
	
	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El nombre del registro.
	 */
	private String nombre;
	
	/**
	 * El autor del registro.
	 */
	private Participante autor;
	
	/**
	 * La fecha del registro.
	 */
	private Fecha fecha;
	
	/**
	 * La hora del registro.
	 */
	private Hora hora;

	
	// ************************************************************************
	// Constructores
	// ************************************************************************

	/**
	 * Construye una registro de una actividad e inicializa sus atributos con 
	 * la información de los parámetros.
	 * 
	 * @param nombreRegistro
	 * @param autor
	 * @param hora
	 */
	public RegistroActividad(String nombre, Participante autor, Fecha fecha, Hora hora) {
		this.nombre = nombre;
		this.autor = autor;
		this.fecha = fecha;
		this.hora = hora;
	}
		
	// ************************************************************************
	// Métodos para consultar los atributos
	// ************************************************************************

	/**
	 * Consulta el nombre del registro.
	 * 
	 * @return nombre
	 */
	public String getNombre() {
		return nombre;
	}
	
	/**
	 * Consulta el autor del registro.
	 * 
	 * @return autor
	 */
	public Participante getAutor() {
		return autor;
	}
	
	/**
	 * Consulta la fecha del registro.
	 * 
	 * @return fecha
	 */
	public Fecha getFecha() {
		return fecha;
	}
	
	/**
	 * Consulta la hora del registro.
	 * 
	 * @return hora
	 */
	public Hora getHora() {
		return hora;
	}
	
}
