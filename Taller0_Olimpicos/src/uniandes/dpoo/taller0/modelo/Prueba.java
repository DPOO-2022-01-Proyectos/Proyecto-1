package uniandes.dpoo.taller0.modelo;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prueba {
	public static void main(String[] args) {
		String valor = input("> ");
		if (valor == "")
			System.out.println("Iguales");
	}
	
	private static String input(String mensaje)	{
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
