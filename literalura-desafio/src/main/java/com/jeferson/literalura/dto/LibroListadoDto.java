package com.jeferson.literalura.dto;

import java.util.List;

public record LibroListadoDto(
    String titulo,
    List<String> autores,
    List<String> idiomas,
    Long descargas
) {}
