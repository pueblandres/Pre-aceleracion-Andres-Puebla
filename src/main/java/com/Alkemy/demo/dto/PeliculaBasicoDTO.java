package com.Alkemy.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaBasicoDTO {
    private String imagen;
    private String titulo;
    private String fechaDeCreacion;
}
