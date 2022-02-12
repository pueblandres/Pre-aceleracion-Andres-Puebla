package com.Alkemy.demo.entidades;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personaje")
@Getter
@Setter
public class PersonajeEntidad {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String imagen;

    private String nombre;

    private Integer edad;

    private Integer peso;

    private String historia;

    @ManyToMany(mappedBy = "personaje", cascade = CascadeType.ALL)
    private List<PeliculaEntidad> peliculas = new ArrayList<>();
}
