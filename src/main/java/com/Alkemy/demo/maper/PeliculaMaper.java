package com.Alkemy.demo.maper;

import com.Alkemy.demo.dto.PeliculaBasicoDTO;
import com.Alkemy.demo.dto.PeliculaDTO;
import com.Alkemy.demo.dto.PersonajeDTO;
import com.Alkemy.demo.entidades.PeliculaEntidad;
import com.Alkemy.demo.entidades.PersonajeEntidad;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class PeliculaMaper {

    @Autowired
    private PersonajeMaper personajeMaper;

    public PeliculaEntidad peliculaDTO2Entidad (PeliculaDTO dto){

        PeliculaEntidad peliculaEntidad = new PeliculaEntidad();
        peliculaEntidad.setImagen(dto.getImagen());
        peliculaEntidad.setTitulo(dto.getTitulo());
        peliculaEntidad.setFechaDeCreacion(string2LocalDate(dto.getFechaDeCreacion()));
        peliculaEntidad.setCalificacion(dto.getCalificacion());
        peliculaEntidad.setGeneroId(dto.getGeneroId());
        List<PersonajeEntidad> personajes = personajeMaper.personajesDTOList2EntidadList(dto.getPersonajes());
        peliculaEntidad.setPersonajes(personajes);
        return peliculaEntidad;
    }

    public PeliculaDTO peliculaEntidad2DTO (PeliculaEntidad entidad,boolean cargarPersonajes){
        PeliculaDTO peliculaDTO = new PeliculaDTO();
        peliculaDTO.setId(entidad.getId());
        peliculaDTO.setImagen(entidad.getImagen());
        peliculaDTO.setTitulo(entidad.getTitulo());
        peliculaDTO.setFechaDeCreacion(entidad.getFechaDeCreacion().toString());
        peliculaDTO.setCalificacion(entidad.getCalificacion());
        peliculaDTO.setGeneroId(entidad.getGeneroId());
        if (cargarPersonajes) {
            List<PersonajeDTO> personajes = personajeMaper.personajeEntidadList2DTOList(entidad.getPersonajes(), false);
            peliculaDTO.setPersonajes(personajes);
        }
        return peliculaDTO;
    }

    public List<PeliculaDTO> peliculaEntidadList2DTOList(List<PeliculaEntidad> entidades, boolean cargarPersonajes) {
        List<PeliculaDTO> dtos = new ArrayList<>();

        for (PeliculaEntidad entidad: entidades) {
            dtos.add(peliculaEntidad2DTO(entidad, cargarPersonajes));
        }
        return dtos;
    }

    private LocalDate string2LocalDate(String fecha) {

        DateTimeFormatter cambio = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        return LocalDate.parse(fecha, cambio);
    }

    public void peliculaEntidadActualizar(PeliculaEntidad entidad, PeliculaDTO dto) {

        entidad.setImagen(dto.getImagen());
        entidad.setTitulo(dto.getTitulo());
        entidad.setFechaDeCreacion(string2LocalDate(dto.getFechaDeCreacion()));
        entidad.setCalificacion(dto.getCalificacion());
        entidad.setGeneroId(dto.getGeneroId());
        entidad.setPersonajes(personajeMaper.personajesDTOList2EntidadList(dto.getPersonajes()));
    }

    public PeliculaBasicoDTO peliculaEntidad2BasicoDTO(PeliculaEntidad entidad) {
        PeliculaBasicoDTO dto = new PeliculaBasicoDTO();
        dto.setImagen(entidad.getImagen());
        dto.setTitulo(entidad.getTitulo());
        dto.setFechaDeCreacion(entidad.getFechaDeCreacion().toString());
        return dto;
    }


    public List<PeliculaBasicoDTO> peliculaEntidadList2BasicoDTOList(List<PeliculaEntidad> entidades) {
        List<PeliculaBasicoDTO> basicoDTOLista = new ArrayList<>();
        for (PeliculaEntidad entidad : entidades) {
            basicoDTOLista.add(peliculaEntidad2BasicoDTO(entidad));
        }
        return basicoDTOLista;
    }
}
