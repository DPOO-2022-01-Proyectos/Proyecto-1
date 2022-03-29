package uniandes.dpoo.taller0.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;

import uniandes.dpoo.taller0.modelo.Fecha;
import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.modelo.Proyecto;
import uniandes.dpoo.taller0.procesamiento.AdministradorDeProyectos;
import uniandes.dpoo.taller0.procesamiento.Cronometro;
import uniandes.dpoo.taller0.procesamiento.Loader;

/*
 * Esta clase se encarga de la comunicación entre el usuario y la lógica del
 * sistema.
 */
public class Aplicacion {
	
	// ************************************************************************
	// Pendientes
	// ************************************************************************

	/*
	 * > 
	 */


	// ************************************************************************
	// Atributos
	// ************************************************************************

	/**
	 * El administrador de proyectos.
	 */
	private AdministradorDeProyectos adminProy;
	
	/**
	 * El cronómetro.
	 */
	private Cronometro cronometro;
	
	/**
	 * La calculadora de estadísticas.
	 */
	//private Calculadora calculadora;
	
	
	// ************************************************************************
	// Métodos principales
	// ************************************************************************
	
	/**
	 * Método main para ejecutar la aplicación.
	 * 
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
	
	
	private void ejecutarAplicacion() throws IOException {
		
		// Cargar información.
		adminProy = new AdministradorDeProyectos();
		adminProy = Loader.cargarInformacion(adminProy);
		
		System.out.println("--------------------------------------------------------");
		System.out.println("-------------- Administrador de Proyectos --------------".toUpperCase());
		System.out.println("--------------------------------------------------------\n");
		
		// Bucle para el menú principal.
		boolean continuar = true;
		while (continuar) {
			try	{
				impMenuPrincipal();
				int opcion_seleccionada = Integer.parseInt(input("> Escoja una opción"));
				
				if (opcion_seleccionada == 1) {
					ejeIniciarSesion();
					ejeProyectos();
				}

				else if (opcion_seleccionada == 2) {
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else {
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e) {
				System.out.println("\n< ERROR FATAL: debe introducir una opción válida.");
				continuar = false;
			}
		}
		
	}

	// ************************************************************************
	// Métodos de impresión
	// ************************************************************************
	
	/**
	 * Imprime el menú principal.
	 */
	private void impMenuPrincipal() {
		System.out.println("1- Iniciar sesión.");
		System.out.println("2- Salir.");
	}
	
	/**
	 * Imprime el menú de proyectos.
	 */
	private void impMenuProyectos() {
		System.out.println("¿Qué desea hacer?");
		System.out.println(" 1- Crear nuevo proyecto.");
		System.out.println(" 2- Manipular proyecto existente.");
	}
	
	/**
	 * Imprime el menú de manipulación de proyectos.
	 */
	private void impMenuManipularProyectos() {
		System.out.println("¿Qué desea hacer?");
		System.out.println(" 1- Añadir participante.");
		System.out.println(" 2- Registrar la realización de una actividad.");
	}
	
	
	// ************************************************************************
	// Métodos para ejecutar opciones
	// ************************************************************************
	
	/**
	 * Pregunta al usuario por su nombre y correo e inicia sesión (o crea un nuevo
	 * participante si dicho no se encontraba registrado).
	 */
	private void ejeIniciarSesion () {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("------------------- Inicio de Sesión -------------------");
		System.out.println("--------------------------------------------------------\n");
		String nombre = input("> Ingrese su nombre");
		String correo = input("> Ingrese su correo");
		
		//Buscar (o crear) participante e inicar sesión.
		Participante participanteActual = adminProy.getParticipantes().get(correo);
		if (participanteActual == null) {
			participanteActual = new Participante(nombre, correo);
			adminProy.añadirParticipanteAMapa(participanteActual);
		}
		
		// TODO Velocidad.
		// participanteActual = adminProy.getParticipantes().get("j.cardenast@uniandes.edu.co");
		
		adminProy.iniciarSesion(participanteActual);
		System.out.println("\n< Bienvenido, " + participanteActual.getNombre() + ".\n");
	}
	
	/**
	 * Imprime el menú general de los proyectos y permite al usuario escoger 
	 * entre dos opciones.
	 */
	private void ejeProyectos() {
		impMenuProyectos();
		int opcionSeleccionada = Integer.parseInt(input("> Escoja una opción"));
		if (opcionSeleccionada == 1) {
			ejeCrearProyecto();
			ejeManipularProyecto();
		}
		else if (opcionSeleccionada == 2) {
			buscarProyecto();
		}
	}
	
	/*
	 * Crea un proyecto con la información ingresada por el usuario.
	 */
	private void ejeCrearProyecto () {
		System.out.println("\n--------------------------------------------------------");
		System.out.println("----------------- Creación de Proyecto -----------------");
		System.out.println("--------------------------------------------------------");
		
		boolean infoNoEsValida = true;
		while (infoNoEsValida) {
			try {
				System.out.println("\nIngrese la siguiente información de su proyecto:");
				String nombre = input(" > Nombre del proyecto");
				String descripcion = input(" > Breve descripción");
				Fecha fechaInicio = new Fecha(input(" > Fecha de inicio (yyyy/MM/dd)"));
				Fecha fechaFin = new Fecha (input(" > Fecha estimada de finalización (yyyy/MM/dd)"));
				
				adminProy.crearNuevoProyecto(nombre, descripcion, fechaInicio, fechaFin);
				adminProy.abrirProyecto(nombre);
				
				infoNoEsValida = false;
			}
			catch (Exception e) {
				System.out.println("\n< ERROR: por favor, ingrese fechas y horas válidas.");
			}
		}
	}
	
	/*
	 * Busca un proyecto entre la lista de proyectos del usuario actual.
	 * Si la búsqueda
	 */
	private void buscarProyecto() {
		ArrayList<String> proyectos = adminProy.getParticipanteActual().getProyectos();
		if (proyectos.size() == 0) {
			System.out.println("\n< ERROR: usted aún no pertenece a ningún proyecto.");
		}
		
		else {	
			System.out.println("\n-------------------------------------------------------------");
			System.out.println("------------------ Escogencia de Proyectos ------------------");
			System.out.println("-------------------------------------------------------------");
			
			boolean invalido = true;
			while (invalido) {
				System.out.println("\nEstos son los proyecto a los que pertenece:");
				for (int i = 0; i < proyectos.size(); i++) {
					System.out.println(" " + (i + 1) + "- " + proyectos.get(i) + ".");
				}
				int opcionSeleccionada = Integer.parseInt(input("> Escoja uno"));
				try {
					adminProy.abrirProyecto(proyectos.get(opcionSeleccionada - 1));
					invalido = false;
					System.out.println("\n< Proyecto escogido: " + proyectos.get(opcionSeleccionada - 1) + ".");
				}
				catch (Exception e) {
					System.out.println("\n< ERROR: ingrese un número adecuado.");
				}
			}
			ejeManipularProyecto();
		}
	}
	
	private void ejeManipularProyecto() {
		System.out.println("\n-------------------------------------------------------------");
		System.out.println("----------------- Manipulación de Proyectos -----------------");
		System.out.println("-------------------------------------------------------------\n");
		
		impMenuManipularProyectos();
		int opcionSeleccionada = Integer.parseInt(input("> Escoja una opción"));
		if (opcionSeleccionada == 1) {
			System.out.println("\nIngrese la siguiente información del nuevo participante:");
			String nombre = input(" > Nombre");
			String correo = input(" > Correo");
			Participante nuevoParticipante = adminProy.getParticipantes().get(correo);
			if (nuevoParticipante == null) {
				adminProy.añadirParticipante(nombre, correo);
			}
			else {
				System.out.println("\n < ERROR: este participante ya hace parte del proyecto.");
			}
		}
		else if (opcionSeleccionada == 2) {
			ejeManipularProyecto();
		}
		
		
	}
	
	
	// ************************************************************************
	// Otros métodos
	// ************************************************************************
		
	/**
	 * Este método sirve para imprimir un mensaje en la consola pidiéndole
	 * información al usuario y luego leer lo que escriba el usuario.
	 * 
	 * @param mensaje El mensaje que se le mostrará al usuario.
	 * 
	 * @return La cadena de caracteres que el usuario escriba como respuesta.
	 */
	private String input(String mensaje)	{
		try	{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e) {
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
}
