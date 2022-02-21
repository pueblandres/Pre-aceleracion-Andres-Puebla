package com.Alkemy.demo.entidades;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity
@Table(name = "genero")
@Getter
@Setter
@SQLDelete(sql = "UPDATE genero SET deleted = true WHERE id=?")
@Where(clause = "deleted = false")
public class GeneroEntidad {

    @Id
    @Column
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String nombre;

    private String imagen;

    private boolean deleted = Boolean.FALSE;

}
