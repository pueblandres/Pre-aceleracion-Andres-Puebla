package com.Alkemy.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaDTO {

    private Long id;
    private String imagen;
    private String titulo;
    private String fechaDeCreacion;
    private Integer calificacion;
    private Long generoId;
    private List<PersonajeDTO> personajes;
}
