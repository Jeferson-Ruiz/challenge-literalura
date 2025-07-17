package com.jeferson.literalura.service;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeferson.literalura.dto.DatosLibroDto;
import com.jeferson.literalura.dto.LibroListadoDto;
import com.jeferson.literalura.dto.ResultadoBusquedaDto;
import com.jeferson.literalura.models.Autor;
import com.jeferson.literalura.models.Libro;
import com.jeferson.literalura.repository.LibroRepository;
import jakarta.transaction.Transactional;

@Service
public class LibroService implements LibroServiceI{

    private ConsumoApi consumoApi = new ConsumoApi();
    private ConvertirDatos convertirDatos = new ConvertirDatos();
    Scanner scanner = new Scanner(System.in);

    @Autowired
    private LibroRepository libroRepository;

    @Override
    public void buscarLibro(){
        System.out.println("Ingrese el nombre del libro que desea buscar");
        String tituloBuscado = scanner.nextLine();

        String url = "https://gutendex.com/books/?search=" + URLEncoder.encode(tituloBuscado, StandardCharsets.UTF_8);
        String json = consumoApi.obtenerDatos(url);

        ResultadoBusquedaDto datos = convertirDatos.obtenerDatos(json, ResultadoBusquedaDto.class);

        if (datos.resultados().isEmpty() || datos == null) {
        System.out.println("No se encontraron libros con ese título.");
        return;
        }

        DatosLibroDto libroDto = datos.resultados().get(0);

        List<Autor> autores = libroDto.autores().stream()
            .map(dto -> new Autor(dto.nombre(), dto.fechaNacimiento(), dto.fechaFallecimiento()))
                .collect(Collectors.toList());

        Libro libro = new Libro(libroDto.titulo(),
                        autores, 
                        libroDto.idiomas(), 
                        libroDto.descargas());

        boolean existe = libroRepository.findAll().stream()
            .anyMatch(l -> l.getTitulo().equalsIgnoreCase(libro.getTitulo()));

        if (existe) {
            System.out.println("El libro ya existe en el sistema");
        }else{
            libroRepository.save(libro);
        }
    }


    @Override
    @Transactional
    public void listarLibros(){
        List<Libro> libros = libroRepository.findAll();

        if (libros.isEmpty()) {
            System.out.println("No existen libros registrados en el sistema");
            return;
        }

        mostrarLibros(libros);
    }

    @Override
    @Transactional
    public void buscarLibrosPorIdioma(String idioma){
        List<Libro> libros = libroRepository.buscarLibrosPorIdima(idioma);

        if (libros.isEmpty()) {
            System.out.println("No existen libros registrados con el idioma "+ idioma);
            return;
        }
        mostrarLibros(libros);
    }


    private void mostrarLibros(List<Libro> libros) {
        List<LibroListadoDto> librosDto = libros.stream()
        .map(libro -> new LibroListadoDto(
            libro.getTitulo(),
            libro.getAutor().stream()
                .map(Autor::getNombre)
                .toList(),
            libro.getIdioma(),
            libro.getDescargas()
        ))
        .toList();

        for (LibroListadoDto libro : librosDto) {
            System.out.println("============================");
            System.out.println("Titulo: " + libro.titulo());
            System.out.println("Autor: " + String.join(", ", libro.autores()));
            System.out.println("Idioma: " + String.join(", ", libro.idiomas()));
            System.out.println("Descargas: " + libro.descargas());
            System.out.println("============================");
        }
    }

}
