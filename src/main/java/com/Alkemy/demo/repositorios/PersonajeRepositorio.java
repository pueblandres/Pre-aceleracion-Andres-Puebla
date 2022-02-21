package com.Alkemy.demo.repositorios;

import com.Alkemy.demo.entidades.PersonajeEntidad;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PersonajeRepositorio extends JpaRepository<PersonajeEntidad, Long> {

    List<PersonajeEntidad> findAll(Specification<PersonajeEntidad> spec);
}
