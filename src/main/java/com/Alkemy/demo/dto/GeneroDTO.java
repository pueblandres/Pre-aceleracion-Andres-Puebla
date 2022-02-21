package com.Alkemy.demo.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GeneroDTO {

    private Long id;
    private String nombre;
    private String imagen;
}
