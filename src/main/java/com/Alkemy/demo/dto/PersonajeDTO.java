package com.Alkemy.demo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PersonajeDTO {
    private Long id;
    private String imagen;
    private String nombre;
    private Integer edad;
    private Integer peso;
    private String historia;
    private List<PeliculaDTO> peliculas;
}
