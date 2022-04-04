package uniandes.dpoo.taller0.modelo;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import uniandes.dpoo.taller0.procesamiento.AdministradorDeProyectos;

public class Prueba {
	public static void main(String[] args) throws IOException {
		File prueba1 = new File(System.getProperty("user.dir") + "/data/prueba1.txt");
		FileWriter fw = new FileWriter(prueba1);
		PrintWriter pw = new PrintWriter(fw);
		
		pw.println("sirve");
		
		pw.close();
		
		
	}
	
	public void guardarNuevaInfo() throws IOException {
		String prueba = System.getProperty("user.dir") + "/data/prueba.txt";
		Path path = Paths.get(prueba);
		Files.delete(path);
	}
}
