package com.jeferson.literalura;

import java.util.InputMismatchException;
import java.util.Scanner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import com.jeferson.literalura.service.AutorService;
import com.jeferson.literalura.service.LibroService;

@SpringBootApplication
public class LiteraluraDesafioApplication implements CommandLineRunner {

	@Autowired
	private LibroService libroService;

	@Autowired
	private AutorService autorService;
	
	public static void main(String[] args) {
		SpringApplication.run(LiteraluraDesafioApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Scanner scanner = new Scanner(System.in);

		int opcionUsuario = -1 ;
		while (opcionUsuario != 0) {
			
			String menu = """
					1. Buscar libro por titulo
					2. Listar libros registrados
					3. Listar autores registrados 
					4. Listar autores vivos en un determinado año
					5. Listar libros por idioma

					0. Salir
					""";

			System.out.println(menu);
			try{
				opcionUsuario = scanner.nextInt();
				scanner.nextLine();
			}catch(InputMismatchException e){
				System.out.println("Valor no permito, Ingrese un valor correspondiente a las opciones del menu");
				scanner.nextLine();
				continue;
			}
			
			switch (opcionUsuario) {				
				case 1 -> libroService.buscarLibro();

				case 2 -> libroService.listarLibros();

				case 3 -> autorService.listarAutores();

				case 4 -> {
					try{
						System.out.println("Ingrese el año del inicio del periodo");
						int anioInicio = Integer.parseInt(scanner.nextLine());

						System.out.println("Ingrese el año del fin del periodo");
						int anioFin = Integer.parseInt(scanner.nextLine());
						
						autorService.ListarAutoresVivos(anioInicio, anioFin);
					} catch(NumberFormatException e) {
						System.out.println("Ingrese un numero entero, correspondiente al año");
					}
				}
				case 5 -> {
					System.out.println("Ingrese el idioma para buscar el libro (es, en, fr)" );
					String idioma = scanner.next();
					libroService.buscarLibrosPorIdioma(idioma);
				}

				case 0 -> {
					System.out.println("Saliendo de la aplicación. ¡Hasta pronto!");
					scanner.close();
				}

				default -> System.out.println("Opcion no valida, vuelta a intentar");
			}
		}
	}

}
