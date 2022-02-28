package com.Alkemy.demo.auth.repositorios;

import com.Alkemy.demo.auth.entidades.UsuarioEntidad;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepositorio  extends JpaRepository<UsuarioEntidad, Long> {

    UsuarioEntidad findByUsername(String username);

}
