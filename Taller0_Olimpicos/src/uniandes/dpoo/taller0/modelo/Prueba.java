package uniandes.dpoo.taller0.modelo;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Prueba {
	public static void main(String[] args) {
		String hora = "12/02/12";
		System.out.println(hora.substring(0,2));
		System.out.println(hora.substring(3,5));
		System.out.println(hora.substring(6,8));
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy/MM/dd HH:mm:ss");
        System.out.println(dtf.format(LocalDateTime.now()).substring(11, 19));
	}
}
