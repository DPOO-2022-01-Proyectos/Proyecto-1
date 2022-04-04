package uniandes.dpoo.taller0.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.HashMap;

import uniandes.dpoo.taller0.modelo.Actividad;
import uniandes.dpoo.taller0.modelo.Fecha;
import uniandes.dpoo.taller0.modelo.Hora;
import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.modelo.Proyecto;
import uniandes.dpoo.taller0.procesamiento.AdministradorDeProyectos;
import uniandes.dpoo.taller0.procesamiento.Calculadora;
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
	 * > Revisar por qué no se guardan los proyectos nuevos y sus participantes.
	 * 
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
	 * La calculadroa estadística.
	 */
	private Calculadora calc;
	
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
	
	/**
	 * Invoca los métodos para ejecutar la aplicación.
	 * 
	 * @throws IOException
	 */
	private void ejecutarAplicacion() throws IOException {
		// Cargar información.
		adminProy = new AdministradorDeProyectos();
		adminProy = Loader.cargarInformacion(adminProy);
		
		// Crear cronómetro y calculadora estadística.
		cronometro = new Cronometro();
		calc = new Calculadora();
		
		impHeader(1);
		
		// Bucle para el menú principal.
		boolean continuar = true;
		while (continuar) {
			try	{
				impMenuPrincipal();
				int opcion_seleccionada = Integer.parseInt(input("> Escoja una opción"));
				
				// Iniciar sesión.
				if (opcion_seleccionada == 1) {
					ejeIniciarSesion(); impHeader(5); ejeProyectos();
				}
				
				// Salir.
				else if (opcion_seleccionada == 2) {
					System.out.println("\n< Gracias por usar la aplicación. Adiós.");
					continuar = false;
					Loader.guardarNuevaInfo(adminProy, null, null, null);
				}
				
				// Opción inválida.
				else {
					System.out.println("\n< ERROR: por favor, escoja una opción válida.");
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
		System.out.println("\n1- Iniciar sesión.");
		System.out.println("2- Salir.");
	}
	
	/**
	 * Imprime el menú de proyectos.
	 */
	private void impMenuProyectos() {
		System.out.println("\n¿Qué desea hacer?");
		System.out.println(" 1- Crear nuevo proyecto.");
		System.out.println(" 2- Manipular proyecto existente.");
		System.out.println(" 3- Regresar.");
		System.out.println(" 4- Revisar estadísticas.");
	}
	
	/**
	 * Imprime el menú de manipulación de proyectos.
	 */
	private void impMenuManipularProyectos() {
		System.out.println("\n¿Qué desea hacer?");
		System.out.println(" 1- Añadir participante.");
		System.out.println(" 2- Registrar la realización de una actividad.");
		System.out.println(" 3- Regresar.");
	}
	
	/**
	 * Imprime el menú para realizar el registro de una actividad.
	 * 
	 * @param opcion
	 */
	private void impMenuRegistroActividad(int opcion) {
		if (opcion == 1) {
			System.out.println("\nDesea realizar el registro de una activdad:");
			System.out.println(" 1- Nueva.");
			System.out.println(" 2- Existente.");
			System.out.println(" 3- Regresar.");
			System.out.println(" 4- Usar el cronómetro.");
		}
	}
	
	/**
	 * Imprime el menú para iniciar cronómetro.
	 */
	private void impCronometroIniciar() {
		System.out.println("\nOpciones del cronómetro:");
		System.out.println(" 1- Iniciar.");
		System.out.println(" 2- Regresar.");
	}
	
	/**
	 * Imprime el menú para pausar cronómetro.
	 */
	private void impCronometroPausar() {
		System.out.println("\nOpciones del cronómetro:");
		System.out.println(" 1- Pausar.");
		System.out.println(" 2- Terminar.");
	}
	
	/**
	 * Imprime el menú para continuar cronómetro.
	 */
	private void impCronometroContinuar() {
		System.out.println("\nOpciones del cronómetro:");
		System.out.println(" 1- Continuar.");
		System.out.println(" 2- Terminar.");
	}
	
	/**
	 * Imprime menu estadísticas.
	 */
	private void impEstadisticas() {
		System.out.println("\n¿Qué desea hacer?");
		System.out.println(" 1- Calcular tiempo total invertido.");
		System.out.println(" 2- Regresar.");
	}
	
	/**
	 * Dada una opción por parámetro, imprime el header correspondiente.
	 * 
	 * @param opcion
	 */
	private void impHeader(int opcion) {
		switch (opcion){
			case 1:
				System.out.println("--------------------------------------------------------");
				System.out.println("-------------- Administrador de Proyectos --------------".toUpperCase());
				System.out.println("--------------------------------------------------------");
				break;
			case 2:
				System.out.println("\n--------------------------------------------------------");
				System.out.println("------------------- Inicio de Sesión -------------------");
				System.out.println("--------------------------------------------------------\n");
				break;
			case 3:
				System.out.println("\n--------------------------------------------------------");
				System.out.println("----------------- Creación de Proyecto -----------------");
				System.out.println("--------------------------------------------------------");
				break;
			case 4:
				System.out.println("\n-------------------------------------------------------------");
				System.out.println("------------------ Escogencia de Proyectos ------------------");
				System.out.println("-------------------------------------------------------------");
				break;
			case 5:
				System.out.println("\n-------------------------------------------------------------");
				System.out.println("----------------- Manipulación de Proyectos -----------------");
				System.out.println("-------------------------------------------------------------");
				break;
			case 6:
				System.out.println("\n-------------------------------------------------------------");
				System.out.println("--------------------- Registro Actividad --------------------");
				System.out.println("-------------------------------------------------------------");
				break;
			case 7:
				System.out.println("\n----------------------------------------------------------");
				System.out.println("--------------------- Nueva Actividad --------------------");
				System.out.println("----------------------------------------------------------");
				break;
			case 8:
				System.out.println("\n--------------------------------------------------------------");
				System.out.println("--------------------- Actividad Existente --------------------");
				System.out.println("--------------------------------------------------------------");
				break;
			case 9:
				System.out.println("\n------------------------------------------------------------");
				System.out.println("--------------------- Creación Registro --------------------");
				System.out.println("------------------------------------------------------------");
				break;
			case 10:
				System.out.println("\n------------------------------------------------------------");
				System.out.println("------------------------- Cronómetro -----------------------");
				System.out.println("------------------------------------------------------------");
				break;
			case 11:
				System.out.println("\n--------------------------------------------------------------");
				System.out.println("------------------------- Estadísticas -----------------------");
				System.out.println("--------------------------------------------------------------");
				break;
		}
	}
	
	// ************************************************************************
	// Métodos para ejecutar opciones
	// ************************************************************************
	
	/**
	 * Pregunta al usuario por su nombre y correo e inicia sesión (o crea un nuevo
	 * participante si dicho no se encontraba registrado).
	 */
	private void ejeIniciarSesion () {
		impHeader(2);
		String nombre = input("> Ingrese su nombre");
		String correo = input("> Ingrese su correo");
		
		//Buscar (o crear) participante e inicar sesión.
		Participante participanteActual = adminProy.getParticipantes().get(correo);
		if (participanteActual == null) {
			participanteActual = new Participante(nombre, correo);
			adminProy.añadirParticipanteAMapa(participanteActual);
		}
		
		// TODO Velocidad.
		participanteActual = adminProy.getParticipantes().get("j.cardenast@uniandes.edu.co");
		
		adminProy.iniciarSesion(participanteActual);
		System.out.println("\n< Bienvenido, " + participanteActual.getNombre() + ".");
	}
	
	
	// ************************************************************************
	// Métodos de los proyectos
	// ************************************************************************
	
	/**
	 * Imprime el menú general de los proyectos y permite al usuario escoger 
	 * entre dos opciones.
	 */
	private void ejeProyectos() {
		boolean seguir = true;
		while (seguir) {
			impMenuProyectos();
			int opcionSeleccionada = Integer.parseInt(input("> Escoja una opción"));
			
			// Crear nuevo proyecto.
			if (opcionSeleccionada == 1) {
				ejeCrearProyecto(); ejeManipularProyecto();
			}
			
			// Manipular proyecto existente.
			else if (opcionSeleccionada == 2) {
				ArrayList<String> proyectos = adminProy.getParticipanteActual().getProyectos();
				if (proyectos.size() == 0)
					System.out.println("\n< ERROR: usted aún no pertenece a ningún proyecto. Escoja otra opción.");
				else
					buscarProyecto(proyectos); ejeManipularProyecto();
			}
			
			// Regresar.
			else if (opcionSeleccionada == 3) {
				seguir = false; System.out.println(); impHeader(1);
			}
			
			// Calcular esadísticas.
			else if (opcionSeleccionada == 4) {
				ArrayList<String> proyectos = adminProy.getParticipanteActual().getProyectos();
				if (proyectos.size() == 0)
					System.out.println("\n< ERROR: usted aún no pertenece a ningún proyecto. Escoja otra opción.");
				else
					ejeEstadisticas();
			}
				
				
		}
	}
	
	/*
	 * Crea un proyecto con la información ingresada por el usuario.
	 */
	private void ejeCrearProyecto () {
		impHeader(3); boolean infoNoEsValida = true;
		while (infoNoEsValida) {
			try {
				System.out.println("\nIngrese la siguiente información de su proyecto:");
				String nombre = input(" > Nombre del proyecto");
				String descripcion = input(" > Breve descripción");
				Fecha fechaInicio = new Fecha(input(" > Fecha de inicio (yyyy/MM/dd)"));
				Fecha fechaFin = new Fecha (input(" > Fecha estimada de finalización (yyyy/MM/dd)"));
				
				adminProy.crearNuevoProyecto(nombre, descripcion, fechaInicio, fechaFin);
				adminProy.abrirProyecto(nombre);
				
				System.out.println("\n< Proyecto '" + nombre + "' creado exitosamente.");
				infoNoEsValida = false;				
			}
			catch (Exception e) {
				System.out.println("\n< ERROR: por favor, ingrese fechas válidas.");
			}
		}
	}
	
	/**
	 * Busca un proyecto entre la lista de proyectos del usuario actual.
	 * 
	 * @param proyectos
	 */
	private void buscarProyecto(ArrayList<String> proyectos) {
		impHeader(4);
		boolean invalido = true;
		while (invalido) {
			System.out.println("\nEstos son los proyecto a los que pertenece:");
			for (int i = 0; i < proyectos.size(); i++)
				System.out.println(" " + (i + 1) + "- " + proyectos.get(i) + ".");

			int opcionSeleccionada = Integer.parseInt(input("> Escoja uno"));
			try {
				adminProy.abrirProyecto(proyectos.get(opcionSeleccionada - 1)); invalido = false;
				System.out.println("\n< Proyecto escogido: " + proyectos.get(opcionSeleccionada - 1) + ".");
			}
			catch (Exception e) {
				System.out.println("\n< ERROR: ingrese un número adecuado.");
			}
		}
	}
	
	/**
	 * Determina las acciones a seguir según la opción que el usuario escoja relacionada
	 * a la manipulación de proyectos.
	 */
	private void ejeManipularProyecto() {
		impHeader(5);
		boolean seguir = true;
		while (seguir ) {
			impMenuManipularProyectos();
			int opcionSeleccionada = Integer.parseInt(input("> Escoja una opción"));
			
			if (opcionSeleccionada == 1)
				añadirParticipante();
			else if (opcionSeleccionada == 2)
				ejeRegistrarActividad();
			else if (opcionSeleccionada == 3)
				seguir = false; impHeader(5);
		}
	}
	
	
	// ************************************************************************
	// Métodos de los participantes
	// ************************************************************************
	
	/**
	 * Añade un participante al proyecto actual.
	 */
	private void añadirParticipante() {
		System.out.println("\nIngrese la siguiente información del nuevo participante:");
		String nombre = input(" > Nombre");
		String correo = input(" > Correo");
		Participante participante = adminProy.getProyectoActual().getParticipantes().get(correo);
		if (participante == null) {
			adminProy.añadirParticipante(nombre, correo);
			System.out.println("\n< Participante añadido con éxito.");
		}
		else {
			System.out.println("\n< ERROR: este participante ya hace parte del proyecto.");
		}
	}
	
	
	// ************************************************************************
	// Métodos de las actividades
	// ************************************************************************
	
	/**
	 * Crea una actividad con la información suministrada por el usuario.
	 * 
	 * @return La actividad creada.
	 */
	private Actividad ejeCrearActividad() {
		impHeader(7);
		Actividad nuevaActividad = null;
		boolean seguir = true;
		while (seguir) {
			try {
				System.out.println("\nIngrese la siguiente información de la actividad:");
				System.out.println("(ACLARACIÓN) Ingrese en 'Fecha' y 'Hora inicio' el número '0' si son las actuales.");
				String titulo = input(" > Título");
				String descripcion = input(" > Breve descripción");
				String tipo = input(" > Tipo");
				
				// Bloque para fecha por defecto.
				Fecha fecha;
				String textoFecha = input(" > Fecha (yyyy/MM/dd)");
				if (Integer.parseInt(textoFecha) == 0)
					fecha = darFechaActual();
				else
					fecha = new Fecha(textoFecha);
				
				// Bloque para hora por defecto.
				Hora horaInicio;
				String textoHoraInicio = input(" > Hora inicio (HH:mm:ss)");
				if (Integer.parseInt(textoHoraInicio) == 0)
					horaInicio = darHoraActual();
				else
					horaInicio= new Hora(textoHoraInicio);
				
				Hora horaFin = new Hora(input(" > Hora de finalización (HH:mm:ss)"));			
				nuevaActividad = new Actividad(titulo, descripcion, tipo, fecha, horaInicio, horaFin);
				adminProy.getProyectoActual().añadirActividad(nuevaActividad);
				System.out.println("\n< Actividad '" + titulo + "' registrada exitosamente.");
				seguir = false;
				}
			
			catch (Exception e) {
				System.out.println("\n< ERROR: por favor, ingrese fechas y horas válidas.");
			}
		}
		return nuevaActividad;
	}
	
	/**
	 * Busca y retorna una actividad en la lista de actividades del proyecto actualmente abierto.
	 * 
	 * @return Actividad escogida.
	 */
	private Actividad buscarActividad() {				
		Actividad actividadEscogida = null;
		boolean invalido = true;
		
		while (invalido) {
			
			HashMap<String, Actividad> actividades = adminProy.getProyectoActual().getActividades();
			ArrayList<Actividad> listaActividades = new ArrayList<>();
			
			// Si el proyecto no tiene actividades.
			if (actividades.size() == 0) {
				System.out.println("\n< ERROR: este proyecto aún no tiene actividades. Cree al menos una.");
				invalido = false;
			}
			
			// El proyecto tiene actividades.
			else {
				for (Actividad actividad:actividades.values()) {
					listaActividades.add(actividad);
				}
				System.out.println("\nEstas son las actividades del proyecto:");
				for (int i = 0; i < listaActividades.size(); i++) {
					System.out.println(" " + (i + 1) + "- " + listaActividades.get(i).getTitulo() + ".");
				}
				int opcionSeleccionada = Integer.parseInt(input("> Escoja una"));
				try {
					switch (opcionSeleccionada) {
						case 0:
							System.out.println("\n< ERROR: ingrese un número adecuado."); break;
						default:
							actividadEscogida = listaActividades.get(opcionSeleccionada - 1);
							invalido = false;
							System.out.println("\n< Actividad escogida: " + listaActividades.get(opcionSeleccionada - 1).getTitulo() + ".");
							break;
					}
				}
				catch (Exception e) {
					System.out.println("\n< ERROR: ingrese un número adecuado.");
				}
			}
			
		}
		return actividadEscogida;
	}
	
	
	// ************************************************************************
	// Métodos de los registros
	// ************************************************************************
	
	/**
	 * Invoa el método que permite crear el regitro de una actividad (dependiendo si
	 * esta existe o no).
	 */
	private void ejeRegistrarActividad() {
		impHeader(6);
		boolean seguir = true;
		while (seguir) {
			impMenuRegistroActividad(1);
			int opcionSeleccionada = Integer.parseInt(input("> Escoja una opción"));
			
			// Si la actividad es nueva.
			if (opcionSeleccionada == 1) {
				Actividad nuevaActividad = ejeCrearActividad();
				ejeCrearRegistro(nuevaActividad); seguir = false;
			}
			
			// Si la actividad ya existe.
			else if (opcionSeleccionada == 2) {
				impHeader(8);
				Actividad actividadExistente = buscarActividad();
				
				// Verificar que se haya encontrado la actividad.
				if (actividadExistente == null)
					seguir = false;
				else
					ejeCrearRegistro(actividadExistente); seguir = false;			
			}
			
			// Regresar.
			else if (opcionSeleccionada == 3) {
				impHeader(5); seguir = false;
			}
			
			// Cronómetro.
			else if (opcionSeleccionada == 4) {
				impHeader(10); ejeCronometro(); seguir = false;
			}
		}
	}
	
	/**
	 * Crea un registro de una actividad que hace parte del proyecto atual.
	 * 
	 * @param actividad
	 */
	private void ejeCrearRegistro(Actividad actividad) {
		impHeader(9);
		boolean seguir = true;
		while (seguir) {
			System.out.println("\nIngrese la siguiente información del registro:");
			System.out.println("(ACLARACIÓN) Ingrese en 'Fecha' y 'Hora' el número '0' si son las actuales.");
			
			// Bloque para fecha por defecto.
			Fecha fecha;
			String textoFecha = input(" > Fecha (yyyy/MM/dd)");
			if (Integer.parseInt(textoFecha) == 0) {
				fecha = darFechaActual();
			}
			else {
				fecha = new Fecha(textoFecha);
			}
			
			// Bloque para hora por defecto.
			Hora hora;
			String textoHoraInicio = input(" > Hora inicio (HH:mm:ss)");
			if (Integer.parseInt(textoHoraInicio) == 0)
				hora = darHoraActual();
			else
				hora = new Hora(textoHoraInicio);
			
			// Bloque para participante.
			String correoParticipante = input(" > Correo del autor ('0' si es usted)");
			Participante participante = adminProy.getProyectoActual().getParticipantes().get(correoParticipante);
			
			// Manejo de error para el método parseInt().
			try {
				if (Integer.parseInt(correoParticipante) == 0) {
					participante = adminProy.getParticipanteActual();
					adminProy.registrarActividad(actividad, participante, fecha, hora);
					System.out.println("\n< Registro exitoso.");
					seguir = false;
				}
			}
			catch (NumberFormatException e) {
				if (participante == null) {
					System.out.println("\n< ERROR: este participante no hace parte del proyecto. Regístrelo primero.");
					seguir = false;
				}
				else {
					adminProy.registrarActividad(actividad, participante, fecha, hora);
					System.out.println("\n< Registro exitoso.");
					seguir = false;
				}
			}	
		}
	}
	
	
	// ************************************************************************
	// Métodos del cronómetro
	// ************************************************************************
	
	/**
	 * Ejecuta el cronómetro
	 */
	private void ejeCronometro() {
		impCronometroIniciar();
		boolean error = true;
		while (error) {
			int opcion = Integer.parseInt(input("> Escoja una opción"));
			
			// Inicializa el cronómetro y ejecuta toda su operación.
			if (opcion == 1) {
				cronometro.inciar();
				boolean pausa = true;
				while (pausa) {
					impCronometroPausar();
					opcion = Integer.parseInt(input("> Escoja una opción"));
					if (opcion == 1) {
						pausar();
						pausa = false;
					}
					
					else {
						System.out.println("\n< ERROR: escoja una opción adecuada.");
					}
				}
				pausar();
				error = false;
			}
			
			// Regresar.
			else if (opcion == 2)
				error = false;
			
			// Error.
			else {
				System.out.println("\n< ERROR: escoja una opción adecuada.");
			}
		}
	}
	
	/**
	 * Pausa el cronómetro y retorna la duración.
	 * 
	 * @return Duración neta.
	 */
	private Hora pausar() {
		Hora duracionNeta = null;
		cronometro.pausar();
		boolean error = true;
		while (error) {
			impCronometroContinuar();
			int opcion = Integer.parseInt(input("> Escoja una opción"));
			if (opcion == 1) {
				continuar();
				error = false;
			}
			else if (opcion == 2) {
				duracionNeta = cronometro.calcularDuracion();
				String cadenaDuracionNeta = duracionNeta.getHora() + ":" + duracionNeta.getMinutos() + ":" + duracionNeta.getSegundos() ;
				System.out.println("\n< La duración de la actividad (en formato HH:mm:ss) fue de :" + cadenaDuracionNeta + "." );
				error = false;
			}
			else {
				System.out.println("\n< ERROR: escoja una opción adecuada.");
			}
		}
		return duracionNeta;
	}
	
	private Hora continuar() {
		Hora duracionNeta = null;
		cronometro.continuar();
		boolean error = true;
		while (error) {
			impCronometroPausar();
			int opcion = Integer.parseInt(input("> Escoja una opción"));
			if (opcion == 1) {
				pausar();
				error = false;
			}
			else if (opcion == 2) {
				duracionNeta = cronometro.calcularDuracion();
				String cadenaDuracionNeta = duracionNeta.getHora() + ":" + duracionNeta.getMinutos() + ":" + duracionNeta.getSegundos();
				System.out.println("\n< La duración de la actividad (en formato HH:mm:ss) fue de: " + cadenaDuracionNeta + "." );
				error = false;
			}
			else {
				System.out.println("\n< ERROR: escoja una opción adecuada.");
			}
		}
		return duracionNeta;
	}
	
	
	// ************************************************************************
	// Métodos de la calculadora
	// ************************************************************************
	
	/**
	 * Ejecuta las opciones relacionadas a lasestadísitcas del participante actual.
	 */
	private void ejeEstadisticas() {
		boolean continuar = true;
		ArrayList<String> proyectos = adminProy.getParticipanteActual().getProyectos();
		impHeader(11);
		while (continuar) {
			impEstadisticas();
			int opcionSeleccionada = Integer.parseInt(input("> Escoja una opción"));
			
			// Tiempo total invertido.
			if (opcionSeleccionada == 1) {
				boolean invalido = true;
				while (invalido) {
					System.out.println("\nEstos son los proyecto a los que pertenece:");
					for (int i = 0; i < proyectos.size(); i++)
						System.out.println(" " + (i + 1) + "- " + proyectos.get(i) + ".");
					int opcionProyecto = Integer.parseInt(input("> Escoja uno"));
					try {
						calc.tiempoTotalInvertido(adminProy.getParticipanteActual(), adminProy.getProyectos().get(proyectos.get(opcionProyecto - 1)));
						invalido = false;
					}
					catch (Exception e) {
						System.out.println("\n< ERROR: ingrese un número adecuado.");
					}
				}
			}
			
			// Regresar.
			else if (opcionSeleccionada == 2) {
				continuar = false; System.out.println(); impHeader(5);
			}
			
			// Error.
			else {
				System.out.println("\n< ERROR: ingrese un número adecuado.");
			}
			
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
	
	/**
	 * Calcula y retorna la fecha actual expresada como un objeto de tipo Fecha.
	 * 
	 * @return La fecha actual.
	 */
	private Fecha darFechaActual () {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		Fecha fechaActual = new Fecha(dtf.format(LocalDateTime.now()).substring(0, 10));
		return fechaActual;
	}
	
	/**
	 * Calcula y retorna la hora actual expresada como un objeto de tipo Hora.
	 * 
	 * @return La hora actual.
	 */
	private Hora darHoraActual () {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
		Hora horaActual = new Hora(dtf.format(LocalDateTime.now()).substring(11, 19));
		return horaActual;
	}
	
}
