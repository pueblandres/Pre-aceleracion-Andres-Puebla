package com.Alkemy.demo.servicios.impl;

import com.Alkemy.demo.dto.GeneroDTO;
import com.Alkemy.demo.entidades.GeneroEntidad;
import com.Alkemy.demo.excepcion.ErrorServicio;
import com.Alkemy.demo.maper.GeneroMaper;
import com.Alkemy.demo.repositorios.GeneroRepositorio;
import com.Alkemy.demo.servicios.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class GeneroServicioImpl implements GeneroServicio {

    @Autowired
    private GeneroMaper generoMaper;

    @Autowired
    private GeneroRepositorio generoRepositorio;

    public GeneroDTO guardar(GeneroDTO dto){

        GeneroEntidad entidad = generoMaper.generoDTO2Entidad(dto);
        GeneroEntidad entidadGuardada = generoRepositorio.save(entidad);
        return  generoMaper.generoEntidad2DTO(entidadGuardada);

    }
    public List<GeneroDTO> todosLosGeneros() {
        List<GeneroEntidad> generoEntidades = generoRepositorio.findAll();
        return generoMaper.generoEntidadList2DTOList(generoEntidades);
    }

    @Override
    public GeneroDTO buscarGeneroPorID(Long Id) {
        Optional<GeneroEntidad> generoEntidad = generoRepositorio.findById(Id);
        if (!generoEntidad.isPresent()) {
            throw new ErrorServicio("El id del genero no es valido");
        }
        return generoMaper.generoEntidad2DTO(generoEntidad.get());
    }


    public GeneroDTO actualizarGenero(Long id, GeneroDTO genero) {
        Optional<GeneroEntidad> generoEntidad = generoRepositorio.findById(id);
        if (!generoEntidad.isPresent()) {
            throw new ErrorServicio("El id del genero no es valido");
        }
        generoMaper.generoEntidadActualizar(generoEntidad.get(), genero);
        GeneroEntidad entidad = generoRepositorio.save(generoEntidad.get());
        return generoMaper.generoEntidad2DTO(entidad);

    }

    public void eliminarGenero(Long id) {
        this.generoRepositorio.deleteById(id);
    }

}
