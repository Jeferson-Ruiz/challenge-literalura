package com.jeferson.literalura.service;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.jeferson.literalura.dto.AutorDto;
import com.jeferson.literalura.dto.AutorLibros;
import com.jeferson.literalura.models.Autor;
import com.jeferson.literalura.models.Libro;
import com.jeferson.literalura.repository.AutorRepository;

@Service
public class AutorService implements IAutorService{

    @Autowired
    private AutorRepository autorRepository;

    @Override
    public void listarAutores(){
        List<Autor> autores = autorRepository.findAll();

        if (autores.isEmpty()) {
            System.out.println("No se encuentran registrados autores en el sistema");
            return;
        }

        List<AutorLibros> autorLibrosDto = autores.stream()
            .map(autor -> new AutorLibros(
                autor.getNombre(), 
                autor.getFechaNacimiento(),
                autor.getFechaFallecimiento(),
                autor.getLibros().stream()
                    .map(Libro::getTitulo)
                    .toList()
                )).toList();

        for (AutorLibros autorDto : autorLibrosDto) {
        System.out.println("\n=================================");
        System.out.println("Autor: " + autorDto.nombre());
        System.out.println("Fecha de nacimiento: " + autorDto.fechaNacimiento());
        System.out.println("Fecha de fallecimiento: " + autorDto.fechaFallecimiento());
        System.out.println("Libros:" +String.join(", ", autorDto.libro()));
        System.out.println("=================================");
        }
    }


    @Override
    public void ListarAutoresVivos(int anioInicio, int anioFin){
        List<Autor> autores = autorRepository.buscarAutoresEnUnPeriodo(anioInicio, anioFin);
        if (autores.isEmpty()) {
            System.out.println("No se econtraton autores vivos entre el año "+ anioInicio + " y el año "+ anioFin);
            return;
        }
            
        List<AutorDto> autorDto = autores.stream()
            .map(autor -> new AutorDto(
                autor.getNombre(),
                autor.getFechaNacimiento(),
                autor.getFechaFallecimiento())).toList();

        for (AutorDto autor : autorDto) {
            System.out.println("\n=================================");
            System.out.println("Autor: " + autor.nombre());
            System.out.println("Fecha de nacimiento: " + autor.fechaNacimiento());
            System.out.println("Fecha de fallecimiento: " + autor.fechaFallecimiento());
            System.out.println("=================================");
        }
    }
}
