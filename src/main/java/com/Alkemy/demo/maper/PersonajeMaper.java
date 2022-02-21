package com.Alkemy.demo.maper;

import com.Alkemy.demo.dto.PeliculaDTO;
import com.Alkemy.demo.dto.PersonajeBasicoDTO;
import com.Alkemy.demo.dto.PersonajeDTO;
import com.Alkemy.demo.entidades.PersonajeEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class PersonajeMaper {

    @Autowired
    private PeliculaMaper peliculaMaper;

    public PersonajeEntidad personajeDTO2Entidad (PersonajeDTO dto){

        PersonajeEntidad personajeEntidad= new PersonajeEntidad();
        personajeEntidad.setImagen(dto.getImagen());
        personajeEntidad.setNombre(dto.getNombre());
        personajeEntidad.setEdad(dto.getEdad());
        personajeEntidad.setPeso(dto.getPeso());
        personajeEntidad.setHistoria(dto.getHistoria());
        return personajeEntidad;
    }
    public List<PersonajeEntidad> personajesDTOList2EntidadList(List<PersonajeDTO> dtos) {
        List<PersonajeEntidad> personajes = new ArrayList<>();

        for (PersonajeDTO dto : dtos) {
            personajes.add(this.personajeDTO2Entidad(dto));
        }
        return personajes;
    }


    public PersonajeDTO personajeEntidad2DTO (PersonajeEntidad entidad, boolean cargarPelicula){

        PersonajeDTO personajeDTO = new PersonajeDTO();
        personajeDTO.setId(entidad.getId());
        personajeDTO.setImagen(entidad.getImagen());
        personajeDTO.setNombre(entidad.getNombre());
        personajeDTO.setEdad(entidad.getEdad());
        personajeDTO.setPeso(entidad.getPeso());
        personajeDTO.setHistoria(entidad.getHistoria());
        if (cargarPelicula) {
            List<PeliculaDTO> peliculasDTOList = peliculaMaper.peliculaEntidadList2DTOList(entidad.getPeliculas(),false);
            personajeDTO.setPeliculas(peliculasDTOList);
        }
        return personajeDTO;
    }

    public List<PersonajeDTO> personajeEntidadList2DTOList(List<PersonajeEntidad> entidades, boolean cargarPelicula) {
        List<PersonajeDTO> dtos = new ArrayList<>();

        for (PersonajeEntidad entidad : entidades) {
            dtos.add(this.personajeEntidad2DTO(entidad, cargarPelicula));
        }
        return dtos;
    }

    public void PersonajeEntidadActualizar(PersonajeEntidad entidad, PersonajeDTO dto) {

        entidad.setImagen(dto.getImagen());
        entidad.setNombre(dto.getNombre());
        entidad.setEdad(dto.getEdad());
        entidad.setPeso(dto.getPeso());
        entidad.setHistoria(dto.getHistoria());
    }

    public List<PersonajeBasicoDTO> personajeEntidadList2BasicoDTOList(List<PersonajeEntidad> entidades) {
        List<PersonajeBasicoDTO> dtoBasicoLista = new ArrayList<>();
        PersonajeBasicoDTO basicoDTO;
        for (PersonajeEntidad entidad : entidades) {
            basicoDTO = new PersonajeBasicoDTO();
            basicoDTO.setImagen(entidad.getImagen());
            basicoDTO.setNombre(entidad.getNombre());
            dtoBasicoLista.add(basicoDTO);
        }
        return dtoBasicoLista;
    }


}
