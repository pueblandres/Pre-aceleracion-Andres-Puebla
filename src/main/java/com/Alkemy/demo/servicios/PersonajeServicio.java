package com.Alkemy.demo.servicios;


import com.Alkemy.demo.dto.PersonajeBasicoDTO;
import com.Alkemy.demo.dto.PersonajeDTO;
import com.Alkemy.demo.entidades.PersonajeEntidad;

import java.util.List;
import java.util.Set;

public interface PersonajeServicio {

    //GET
    PersonajeEntidad buscarEntidadPorID(Long personajeId);

    List<PersonajeBasicoDTO> personajesBasicos();

    PersonajeDTO buscarPersonajePorID(Long characterId);

    //GET BY FILTERS
    List<PersonajeDTO> buscarPersonajesPorFiltro(String nombre, Integer edad, Set<Long> peliculas);

    //SAVE
    PersonajeDTO guardarPersonaje(PersonajeDTO personajeDTO);

    //UPDATE
    PersonajeDTO actualizarPersonaje(Long id, PersonajeDTO personaje);

    //DELETE
    void eliminarPersonaje(Long id);

}
