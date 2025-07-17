package com.jeferson.literalura.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record ResultadoBusquedaDto(
    @JsonAlias("results") List<DatosLibroDto> resultados
) {}
