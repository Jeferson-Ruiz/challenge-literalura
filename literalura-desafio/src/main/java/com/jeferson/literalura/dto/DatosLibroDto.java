package com.jeferson.literalura.dto;

import java.util.List;
import com.fasterxml.jackson.annotation.JsonAlias;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public record DatosLibroDto(
    @JsonAlias("title")String titulo,
    @JsonAlias("authors") List<AutorDto> autores,
    @JsonAlias("languages") List<String> idiomas,
    @JsonAlias("download_count") Long descargas
) {

}
