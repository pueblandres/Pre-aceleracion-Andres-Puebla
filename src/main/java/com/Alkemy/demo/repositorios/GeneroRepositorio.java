package com.Alkemy.demo.repositorios;

import com.Alkemy.demo.entidades.GeneroEntidad;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface GeneroRepositorio extends JpaRepository<GeneroEntidad, Long> {
}
