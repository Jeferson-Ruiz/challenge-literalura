package com.jeferson.literalura.dto;

import java.util.List;

public record AutorLibros(
    String nombre,
    Integer fechaNacimiento,
    Integer fechaFallecimiento,
    List<String> libro
) {}