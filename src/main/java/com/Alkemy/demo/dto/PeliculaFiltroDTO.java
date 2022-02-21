package com.Alkemy.demo.dto;


import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class PeliculaFiltroDTO {

    private String titulo;
    private String genero;
    private String orden;

    public PeliculaFiltroDTO(String titulo, String genero, String orden) {
        this.titulo = titulo;
        this.genero = genero;
        this.orden = orden;
    }

    public boolean ordenASC() {
        return orden.compareToIgnoreCase("ASC") == 0 ;
    }
    public boolean ordenDESC() {
        return orden.compareToIgnoreCase("DESC") == 0 ;
    }
}
