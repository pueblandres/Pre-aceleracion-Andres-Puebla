package com.Alkemy.demo.servicios.impl;

import com.Alkemy.demo.dto.PersonajeBasicoDTO;
import com.Alkemy.demo.dto.PersonajeDTO;
import com.Alkemy.demo.dto.PersonajeFiltroDTO;
import com.Alkemy.demo.entidades.PersonajeEntidad;
import com.Alkemy.demo.excepcion.ErrorServicio;
import com.Alkemy.demo.maper.PersonajeMaper;
import com.Alkemy.demo.repositorios.PersonajeRepositorio;
import com.Alkemy.demo.repositorios.especificaciones.PersonajeEspecificacion;
import com.Alkemy.demo.servicios.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class PersonajeServicioImpl implements PersonajeServicio {

    @Autowired
    private PersonajeMaper personajeMaper;

    @Autowired
    private PersonajeRepositorio personajeRepositorio;

    @Autowired
    private PersonajeEspecificacion personajeEspecificacion;

    public PersonajeEntidad buscarEntidadPorID(Long personajeId) {
        Optional<PersonajeEntidad> personaje = personajeRepositorio.findById(personajeId);
        if (!personaje.isPresent()) {
            throw new ErrorServicio("El id de personaje no es valido");
        }
        return personaje.get();
    }

    public List<PersonajeBasicoDTO> personajesBasicos() {
        List<PersonajeEntidad> personajeEntidadBasicoList = personajeRepositorio.findAll();
        return personajeMaper.personajeEntidadList2BasicoDTOList(personajeEntidadBasicoList);
    }

    public PersonajeDTO buscarPersonajePorID(Long personajeId) {
        Optional<PersonajeEntidad> personaje = personajeRepositorio.findById(personajeId);
        if (!personaje.isPresent()) {
            throw new ErrorServicio("El id de personaje no es valido");
        }
        return personajeMaper.personajeEntidad2DTO(personaje.get(), true);
    }

    public PersonajeDTO guardarPersonaje(PersonajeDTO personajeDTO) {
        PersonajeEntidad entidad = personajeMaper.personajeDTO2Entidad(personajeDTO);
        PersonajeEntidad entidadGuardada = personajeRepositorio.save(entidad);
        return personajeMaper.personajeEntidad2DTO(entidadGuardada, false);

    }

    public void eliminarPersonaje(Long id) {
        personajeRepositorio.deleteById(id);
    }

    public PersonajeDTO actualizarPersonaje(Long id, PersonajeDTO personajeDTO){
        Optional<PersonajeEntidad> entidad = personajeRepositorio.findById(id);
        if (!entidad.isPresent()) {
            throw new ErrorServicio("El id de personaje no es valido");
        }
        personajeMaper.PersonajeEntidadActualizar(entidad.get(), personajeDTO);
        PersonajeEntidad entidadGuardada = personajeRepositorio.save(entidad.get());
        return personajeMaper.personajeEntidad2DTO(entidadGuardada, false);
    }

    public List<PersonajeDTO> buscarPersonajesPorFiltro(String nombre, Integer edad, Set<Long> peliculas) {
        PersonajeFiltroDTO personajeFiltroDTO = new PersonajeFiltroDTO(nombre, edad, peliculas);
        List<PersonajeEntidad> entidades = personajeRepositorio.findAll(personajeEspecificacion.buscarPersonajesPorFiltro(personajeFiltroDTO));
        return personajeMaper.personajeEntidadList2DTOList(entidades, true);
    }


}
