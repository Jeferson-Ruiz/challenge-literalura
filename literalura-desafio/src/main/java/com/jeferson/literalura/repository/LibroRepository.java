package com.jeferson.literalura.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import com.jeferson.literalura.models.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long> {

    @Query("SELECT l FROM Libro l WHERE :idioma IN elements(l.idioma)")
    List<Libro> buscarLibrosPorIdima(String idioma);

}
