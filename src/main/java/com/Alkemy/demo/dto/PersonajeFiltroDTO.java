package com.Alkemy.demo.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PersonajeFiltroDTO {
    private String nombre;
    private Integer edad;
    private Set<Long> peliculas;
}

