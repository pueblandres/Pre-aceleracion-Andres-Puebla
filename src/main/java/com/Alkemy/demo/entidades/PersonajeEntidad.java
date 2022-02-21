package com.Alkemy.demo.entidades;

import com.Alkemy.demo.entidades.PeliculaEntidad;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "personaje")
@Getter
@Setter
@SQLDelete(sql = "UPDATE personaje SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
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

    private boolean deleted = Boolean.FALSE;

    @ManyToMany(mappedBy = "personajes", cascade = CascadeType.ALL)
    private List<PeliculaEntidad> peliculas = new ArrayList<>();
}
