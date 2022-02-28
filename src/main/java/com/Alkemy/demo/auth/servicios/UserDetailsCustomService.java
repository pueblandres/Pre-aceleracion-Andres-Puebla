package com.Alkemy.demo.auth.servicios;
import com.Alkemy.demo.auth.dto.UsuarioDTO;
import com.Alkemy.demo.auth.entidades.UsuarioEntidad;
import com.Alkemy.demo.auth.exepcion.UserAlreadyExistException;
import com.Alkemy.demo.auth.repositorios.UsuarioRepositorio;
import com.Alkemy.demo.servicios.EmailServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import org.springframework.stereotype.Service;

import java.util.Collections;

@Service
public class UserDetailsCustomService implements UserDetailsService {

    @Autowired
    private UsuarioRepositorio usuarioRepositorio;

    @Autowired
    private EmailServicio emailServicio;

    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        UsuarioEntidad userEntity = usuarioRepositorio.findByUsername(userName);
        if (userEntity == null) {
            throw new UsernameNotFoundException("Usuario o contraseña no encontrada");
        }

        return new User(userEntity.getUsername(),userEntity.getPassword(), Collections.emptyList());
    }

    public boolean checkIfUserExist(String username) {
        return usuarioRepositorio.findByUsername(username) != null;
    }

    public boolean save(UsuarioDTO userDTO) throws UserAlreadyExistException {
        if(checkIfUserExist(userDTO.getUsername())){
            throw new UserAlreadyExistException("User already exists");
        }
        UsuarioEntidad userEntity = new UsuarioEntidad();
        userEntity.setUsername(userDTO.getUsername());
        String encriptada = new BCryptPasswordEncoder().encode(userDTO.getPassword());
        userEntity.setPassword(encriptada);

        userEntity = this.usuarioRepositorio.save(userEntity);

        if (userEntity != null) {
            emailServicio.sendWelcomeEmailTo(userEntity.getUsername());
        }
        return userEntity != null;
    }

}