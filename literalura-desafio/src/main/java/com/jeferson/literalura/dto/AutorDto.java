package com.jeferson.literalura.dto;

import com.fasterxml.jackson.annotation.JsonAlias;

public record AutorDto(
        @JsonAlias("name") String nombre,
        @JsonAlias("birth_year") Integer fechaNacimiento,
        @JsonAlias("death_year") Integer fechaFallecimiento
){
}