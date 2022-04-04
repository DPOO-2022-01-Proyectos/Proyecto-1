package uniandes.dpoo.taller0.procesamiento;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;

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
	 * 
	 * @throws IOException 
	 */
	public static AdministradorDeProyectos cargarInformacion(AdministradorDeProyectos adminProyVacio) throws IOException {
		AdministradorDeProyectos adminProy = adminProyVacio;
		String archivoParticipantes = System.getProperty("user.dir") + "/data/participantes.txt";
		String archivoProyectos = System.getProperty("user.dir") + "/data/proyectos.txt";
		String archivoActividades = System.getProperty("user.dir") + "/data/actividades.txt";
		String archivoRegistros = System.getProperty("user.dir") + "/data/registros.txt";
		
		adminProy = cargarParticipantes(adminProy, archivoParticipantes);
		adminProy = cargarProyectos(adminProy, archivoProyectos);
		adminProy = cargarActividades(adminProy, archivoActividades);		
		adminProy = cargarRegistros(adminProy, archivoRegistros);
		
		return adminProy;
	}
	
	/**
	 * Lee la información del archivo de participantes, crea objetos de tipo Participante
	 * y los guarda en las estructuras de datos del objeto adminProy pasado por parámetro.
	 * 
	 * @param adminProyVacio
	 * @param archivoParticipantes
	 * 
	 * @throws IOException
	 */
	private static AdministradorDeProyectos cargarParticipantes(AdministradorDeProyectos adminProyVacio, String archivoParticipantes) throws IOException {
		AdministradorDeProyectos adminProy = adminProyVacio;
		BufferedReader br = new BufferedReader(new FileReader(archivoParticipantes));
		String linea = br.readLine();
		linea = br.readLine();
		
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
		}
		br.close();
		return adminProy;
	}
	
	/**
	 * Lee la información del archivo de proyectos, crea objetos de tipo Proyecto
	 * y los guarda en las estructuras de datos del objeto adminProy pasado por parámetro.
	 * 
	 * @param adminProyVacio
	 * @param archivoProyectos
	 * 
	 * @throws IOException
	 */
	private static AdministradorDeProyectos cargarProyectos(AdministradorDeProyectos adminProyVacio, String archivoProyectos) throws IOException {
		AdministradorDeProyectos adminProy = adminProyVacio;
		BufferedReader br = new BufferedReader(new FileReader(archivoProyectos));
		String linea = br.readLine();
		linea = br.readLine();
		
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
		return adminProy;
	}	
	
	/**
	 * Lee la información del archivo de actividades, crea objetos de tipo Actividad
	 * y los guarda en las estructuras de datos del objeto adminProy pasado por parámetro.
	 * 
	 * @param adminProyVacio
	 * @param archivoActividades
	 * 
	 * @throws IOException
	 */
	private static AdministradorDeProyectos cargarActividades(AdministradorDeProyectos adminProyVacio, String archivoActividades) throws IOException {
		AdministradorDeProyectos adminProy = adminProyVacio;
		BufferedReader br = new BufferedReader(new FileReader(archivoActividades));
		String linea = br.readLine();
		linea = br.readLine();
		
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
		return adminProy;
	}
	
	/**
	 * Lee la información del archivo de registros, crea objetos de tipo RegistroDeActividad
	 * y los guarda en las estructuras de datos del objeto adminProy pasado por parámetro.
	 * 
	 * @param adminProyVacio
	 * @param archivoRegistros
	 * 
	 * @throws IOException
	 */
	private static AdministradorDeProyectos cargarRegistros(AdministradorDeProyectos adminProyVacio, String archivoRegistros) throws IOException {
		AdministradorDeProyectos adminProy = adminProyVacio;
		BufferedReader br = new BufferedReader(new FileReader(archivoRegistros));
		String linea = br.readLine();
		linea = br.readLine();
		
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
			
			linea = br.readLine();
		}
		br.close();
		return adminProy;
	}
	
	/**
	 * Carga la información nueva.
	 * 
	 * @param adminProy
	 * @param archivoProyectos
	 * @param archivoParticipantes
	 * @param archivoRegistros
	 * 
	 * @throws IOException
	 */
	public static void guardarNuevaInfo(AdministradorDeProyectos adminProy, String archivoProyectos, String archivoParticipantes, String archivoRegistros) throws IOException {
		
		HashMap<String, Proyecto> proyectos = adminProy.getProyectos();
		HashMap<String, Participante> participantes = adminProy.getParticipantes();
		
		String pathParticipantes = System.getProperty("user.dir") + "/data/participantes.txt";
		Path path = Paths.get(pathParticipantes);
		Files.delete(path);
		
		File archivo = new File(System.getProperty("user.dir") + "/data/participantes.txt");
		FileWriter fw = new FileWriter(archivo);
		PrintWriter pw = new PrintWriter(fw);
		
		
		pw.println("correo;nombre;proyecto1;...;proyecton");
		String linea;
		for (Participante participante:participantes.values()){
			linea = "";
			String correo = participante.getCorreo(); linea += correo; 
			String nombre = participante.getNombre(); linea += ";" + nombre;
			for (String proyecto:participante.getProyectos()) {
				linea += ";" + proyecto;
			}
			pw.println(linea);
		}
		pw.close();
		
		String pathProyectos = System.getProperty("user.dir") + "/data/proyectos.txt";
		path = Paths.get(pathProyectos);
		Files.delete(path);
		
		archivo = new File(System.getProperty("user.dir") + "/data/proyectos.txt");
		fw = new FileWriter(archivo);
		pw = new PrintWriter(fw);
		
		pw.println("nombre;descripcion;fechaI;fechaF;participantes");
		for (Proyecto proyecto:proyectos.values()){
			linea = "";
			String nombre = proyecto.getNombre(); linea += nombre; 
			String descripcion = proyecto.getDescripcion(); linea += ";" + descripcion;
			String fechaI = proyecto.getFechaInicio().fechaString(); linea += ";" + fechaI;
			String fechaF = proyecto.getFechaFin().fechaString();linea += ";" + fechaF;
			for (Participante participante:proyecto.getParticipantes().values()) {
				linea += ";" + participante.getCorreo();
			}
			pw.println(linea);
		}
		pw.close();
		
		String pathActividades = System.getProperty("user.dir") + "/data/actividades.txt";
		path = Paths.get(pathActividades);
		Files.delete(path);
		
		archivo = new File(System.getProperty("user.dir") + "/data/actividades.txt");
		fw = new FileWriter(archivo);
		pw = new PrintWriter(fw);
		pw.println("nombreProyecto;título;descripción;tipo;fecha;horaI;horaF");
		
		for (Proyecto proyecto:proyectos.values()){
			linea = "";
			for (Actividad actividad:proyecto.getActividades().values()) {
				linea = "";
				String nombre = proyecto.getNombre(); linea += nombre;
				String titulo = actividad.getTitulo(); linea += ";" + titulo;
				String descripcion = actividad.getDescripcion(); linea += ";" + descripcion;
				String tipo = actividad.getTipo(); linea += (";" + tipo);
				String fecha = actividad.getFechaRealizacion().fechaString(); linea += ";" + fecha;
				String horaI = actividad.getHoraInicio().horaString(); linea += ";" + horaI;
				String horaF = actividad.getHoraFin().horaString(); linea += ";" + horaF;
				pw.println(linea);
			}
		}
		pw.close();
		
		String pathRegistros = System.getProperty("user.dir") + "/data/registros.txt";
		path = Paths.get(pathRegistros);
		Files.delete(path);
		
		archivo = new File(System.getProperty("user.dir") + "/data/registros.txt");
		fw = new FileWriter(archivo);
		pw = new PrintWriter(fw);
		
		pw.println("nombre;correoAutor;fecha;hora;nombreProyecto");
		for (Proyecto proyecto:proyectos.values()){
			linea = "";
			for (Actividad actividad:proyecto.getActividades().values()) {
				for (RegistroActividad registro:actividad.getRegistros()) {
					linea = "";
					String nombre = registro.getNombre(); linea += nombre;
					String correo = registro.getAutor().getCorreo(); linea += ";" + correo;
					String fecha = registro.getFecha().fechaString(); linea += ";" + fecha;
					String hora = registro.getHora().horaString(); linea += ";" + hora;
					String nombreProyecto = proyecto.getNombre(); linea += ";" + nombreProyecto;
					pw.println(linea);
				}
			}
		}
		
		pw.close();
		
	}
	
}
