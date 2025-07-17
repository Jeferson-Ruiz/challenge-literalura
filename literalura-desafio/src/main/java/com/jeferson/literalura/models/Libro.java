package com.jeferson.literalura.models;

import java.util.List;
import java.util.stream.Stream;

import com.jeferson.literalura.dto.AutorDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "libros")
public class Libro {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String titulo;

    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "libro_autores",
        joinColumns = @JoinColumn(name = "libro_id"),
        inverseJoinColumns = @JoinColumn(name = "autor_id"))
    private List<Autor> autores;

    @ElementCollection	
    private List<String> idioma;

    private Long descargas;

    public Libro(){}

    public Libro(String titulo, List<Autor> autores, List<String> idioma, Long descargas) {
        this.titulo = titulo;
        this.autores = autores;
        this.idioma = idioma;
        this.descargas = descargas;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public List<Autor> getAutor() {
        return autores;
    }

    public void setAutor(List<Autor> autor) {
        this.autores = autor;
    }

    public List<String> getIdioma() {
        return idioma;
    }

    public void setIdioma(List<String> idioma) {
        this.idioma = idioma;
    }

    public Long getDescargas() {
        return descargas;
    }

    public void setDescargas(Long descargas) {
        this.descargas = descargas;
    }

    public Stream<AutorDto> stream() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'stream'");
    }
}
