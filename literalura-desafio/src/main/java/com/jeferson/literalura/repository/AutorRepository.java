package com.jeferson.literalura.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.jeferson.literalura.models.Autor;
import java.util.List;

public interface AutorRepository extends JpaRepository<Autor, Long> {

    List<Autor> findByNombre(String nombre);

    @Query("SELECT a FROM Autor a WHERE a.fechaNacimiento <= :anioFin AND (a.fechaFallecimiento IS NULL OR a.fechaFallecimiento >= :anioInicio)")
    List<Autor> buscarAutoresEnUnPeriodo(@Param("anioInicio") Integer anioInicio, @Param("anioFin") Integer anioFin);
}
