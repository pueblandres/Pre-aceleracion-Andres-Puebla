package com.Alkemy.demo.repositorios;

import com.Alkemy.demo.entidades.PeliculaEntidad;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PeliculaRepositorio extends JpaRepository<PeliculaEntidad, Long> {

    List<PeliculaEntidad> findAll(Specification<PeliculaEntidad> spec);
}
