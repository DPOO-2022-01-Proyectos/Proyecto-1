package uniandes.dpoo.taller0.procesamiento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.taller0.modelo.Actividad;
import uniandes.dpoo.taller0.modelo.Fecha;
import uniandes.dpoo.taller0.modelo.Hora;
import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.modelo.Proyecto;
import uniandes.dpoo.taller0.modelo.RegistroActividad;

/*
 * Esta clase se encarga de cargar toda la información de las bases de datos.
 */
public class Loader {

	// ************************************************************************
	// Métodos para cargar la información
	// ************************************************************************

	/**
	 * Carga toda la información llamando a los métodos respectivos a cada archivo.
	 * @throws IOException 
	 */
	public static AdministradorDeProyectos cargarInformacion(AdministradorDeProyectos adminProy) throws IOException {
		String archivoParticipantes = System.getProperty("user.dir") + "/data/participantes.txt";
		String archivoProyectos = System.getProperty("user.dir") + "/data/proyectos.txt";
		String archivoActividades = System.getProperty("user.dir") + "/data/actividades.txt";
		String archivoRegistros = System.getProperty("user.dir") + "/data/registros.txt";
		
		cargarParticipantes(adminProy, archivoParticipantes);
		cargarProyectos(adminProy, archivoProyectos);
		cargarActividades(adminProy, archivoActividades);
		cargarRegistros(adminProy, archivoRegistros);
		
		return adminProy;
	}
	
	/**
	 * 
	 * @throws IOException
	 */
	private static void cargarParticipantes(AdministradorDeProyectos adminProy, String archivoParticipantes) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoParticipantes));
		String linea = br.readLine();
		
		// Leer línea a línea hasta que se llegue a la última.
		while (linea != null) {
			String[] partes = linea.split(";");
			
			// Crear el participante y guardarlo.
			String correo = partes[0];
			String nombre = partes[1];
			Participante nuevoParticipante = new Participante(nombre, correo);
			for (int i = 2; i < partes.length; i++) {
				nuevoParticipante.añadirProyecto(partes[i]);
			}
			adminProy.añadirParticipanteAMapa(nuevoParticipante);
			
			linea = br.readLine();
			
			// TODO Cargar registros.
		}
		br.close();
	}
	
	/**
	 * 
	 * 
	 * @throws IOException
	 */
	private static void cargarProyectos(AdministradorDeProyectos adminProy, String archivoProyectos) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoProyectos));
		String linea = br.readLine();
		
		// Leer línea a línea hasta que se llegue a la última.
		while (linea != null) {
			// Separar las partes de la línea y guardarlas.
			String[] partes = linea.split(";");
			
			// Crear el proyecto y su información y guardarlo.
			String nombre = partes[0];
			String descripcion = partes[1];
			Fecha fechaInicio = new Fecha(partes[2]);
			Fecha fechaFin = new Fecha(partes[3]);
			Participante participanteInicial = adminProy.getParticipantes().get(partes[4]);
			Proyecto nuevoProyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, participanteInicial);
			for (int i = 4; i < partes.length; i++) {
				Participante participante = adminProy.getParticipantes().get(partes[i]);
				nuevoProyecto.añadirParticipante(participante);
			}
			adminProy.añadirProyecto(nuevoProyecto);
			
			linea = br.readLine();
		}
		br.close();
	}	
	
	private static void cargarActividades(AdministradorDeProyectos adminProy, String archivoActividades) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoActividades));
		String linea = br.readLine();
		
		// Leer línea a línea hasta que se llegue a la última.
		while (linea != null) {
			String[] partes = linea.split(";");
			
			// Crear la actividad y guardarla.
			String titulo = partes[1];
			String descripcion = partes[2];
			String tipo = partes[3];
			Fecha fecha = new Fecha(partes[4]);
			Hora horaInicio = new Hora(partes[5]);
			Hora horaFin = new Hora(partes[6]);
			Actividad nuevaActividad = new Actividad(titulo, descripcion, tipo, fecha, horaInicio, horaFin);
			
			//Añadir la actividad al proyecto al que pertenece.
			Proyecto proyecto = adminProy.getProyectos().get(partes[0]);
			proyecto.añadirActividad(nuevaActividad);
			
			linea = br.readLine();
		}
		br.close();
	}
	
	private static void cargarRegistros(AdministradorDeProyectos adminProy, String archivoRegistros) throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoRegistros));
		String linea = br.readLine();
		
		// Leer línea a línea hasta que se llegue a la última.
		while (linea != null) {
			String[] partes = linea.split(";");
			
			// Crear el registro y guardarlo.
			String nombre = partes[0];
			String correoAutor = partes[1];
			Participante autor = adminProy.getParticipantes().get(correoAutor);
			Fecha fecha = new Fecha(partes[2]);
			Hora hora = new Hora(partes[3]);
			String nombreProyecto = partes[4];
			RegistroActividad nuevoRegistro = new RegistroActividad(nombre, autor, fecha, hora);
			
			Proyecto proyecto = adminProy.getProyectos().get(nombreProyecto);
			autor.añadirRegistroActividad(nuevoRegistro);
			Actividad actividad = proyecto.getActividades().get(nombre);
			actividad.añadirRegistro(nuevoRegistro);
			
			//TODO Añadir registro a participante y actvidad.
			
			linea = br.readLine();
		}
		br.close();
	}
	
	
	
}
