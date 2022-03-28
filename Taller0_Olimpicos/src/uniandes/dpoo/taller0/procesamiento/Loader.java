package uniandes.dpoo.taller0.procesamiento;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import uniandes.dpoo.taller0.modelo.Fecha;
import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.modelo.Proyecto;

/*
 * Esta clase se encarga de cargar toda la información de las bases de datos.
 */
public class Loader {
	
	private AdministradorDeProyectos administradorDeProyectos;
	private String archivoProyectos;
	private String archivoParticipantes;
	
	private void buscarArchivos() {
		archivoProyectos = System.getProperty("user.dir") + "/data/proyectos.txt";
		archivoParticipantes = System.getProperty("user.dir") + "/data/participantes.txt";
	}
	
	private void cargarProyectos() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoProyectos));
		String linea = br.readLine();
		
		// Leer línea a línea hasta que se llegue a la última.
		while (linea != null) {
			// Separar las partes de la línea y guardarlas.
			String[] partes = linea.split(";");
			
			// Crear el ingrediente y su información y guardarlo en ingredientes.
			String nombre = partes[0];
			String descripcion = partes[1];
			Fecha fechaInicio = new Fecha(partes[2]);
			Fecha fechaFin = new Fecha(partes[3]);
			ArrayList<String> participantes = new ArrayList<String>();
			for (int i = 4; i < partes.length; i++) {
				participantes.add(partes[i]);
			}
			Participante participanteInicial = administradorDeProyectos.getParticipantes().get(partes[4]);
			Proyecto nuevoProyecto = new Proyecto(nombre, descripcion, fechaInicio, fechaFin, participanteInicial);
			administradorDeProyectos.añadirProyecto(nuevoProyecto);
			
			// Leer la siguiente línea.
			linea = br.readLine();
			
		}
		br.close();
	}
	
	private void cargarParticipantes() throws IOException {
		BufferedReader br = new BufferedReader(new FileReader(archivoProyectos));
		String linea = br.readLine();
		
		// Leer línea a línea hasta que se llegue a la última.
		while (linea != null) {
			// Separar las partes de la línea y guardarlas.
			String[] partes = linea.split(";");
			
			// Crear el ingrediente y su información y guardarlo en ingredientes.
			String correo = partes[0];
			String nombre = partes[1];
			ArrayList<String> proyectos = new ArrayList<String>();
			for (int i = 3; i < partes.length; i++) {
				proyectos.add(partes[i]);
			}
			Participante  nuevoParticipante = new Participante(nombre, correo);
			administradorDeProyectos.añadirParticipanteAMapa(nuevoParticipante);
			
			// Leer la siguiente línea.
			linea = br.readLine();
			
		}
		br.close();
	}
	
	private void cargarInformacion() {
		buscarArchivos();
		cargarProyectos();
	}
	
}
