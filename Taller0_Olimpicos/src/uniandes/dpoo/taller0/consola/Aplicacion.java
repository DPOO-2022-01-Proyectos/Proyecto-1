package uniandes.dpoo.taller0.consola;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import uniandes.dpoo.taller0.modelo.Participante;
import uniandes.dpoo.taller0.procesamiento.AdministradorDeProyectos;
import uniandes.dpoo.taller0.procesamiento.Cronometro;

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
	private AdministradorDeProyectos administradorDeProyectos;
	
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
	 */
	public static void main(String[] args) {
		Aplicacion aplicacion = new Aplicacion();
		aplicacion.ejecutarAplicacion();
	}
	
	private void ejecutarAplicacion() {
		System.out.println("\nAdministrados de Proyectos\n");
		
		// Bucle para el menú principal.
		boolean continuar = true;
		while (continuar) {
			try	{
				impMenuPrincipal();
				int opcion_seleccionada = Integer.parseInt(input("> Seleccione una opción"));
				if (opcion_seleccionada == 1)
					ejeIniciarSesion();

				else if (opcion_seleccionada == 2)
				{
					System.out.println("Saliendo de la aplicación ...");
					continuar = false;
				}
				else
				{
					System.out.println("Por favor seleccione una opción válida.");
				}
			}
			catch (NumberFormatException e)
			{
				System.out.println("Debe seleccionar uno de los números de las opciones.");
			}
		}
		
	}

	// ************************************************************************
	// Métodos de impresión
	// ************************************************************************
	
	private void impMenuPrincipal() {
		System.out.println("1- Iniciar sesión.");
		System.out.println("2- Salir.");
	}
	
	private void impIniciarSesion() {
		System.out.println();
	}
	
	
	// ************************************************************************
	// Métodos para ejecutar opciones
	// ************************************************************************
	
	private void ejeIniciarSesion () {
		System.out.println("\nInicio de sesión:");
		String nombre = input(" > Ingrese su nombre");
		String correo = input(" > Ingrese su correo");
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
	public String input(String mensaje)
	{
		try
		{
			System.out.print(mensaje + ": ");
			BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
			return reader.readLine();
		}
		catch (IOException e)
		{
			System.out.println("Error leyendo de la consola");
			e.printStackTrace();
		}
		return null;
	}
	
}
