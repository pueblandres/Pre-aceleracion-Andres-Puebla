package com.Alkemy.demo.servicios;

import com.Alkemy.demo.dto.PeliculaBasicoDTO;
import com.Alkemy.demo.dto.PeliculaDTO;
import java.util.List;
public interface PeliculaServicio {


    List<PeliculaBasicoDTO> buscarPeliculasBasico();

    PeliculaDTO buscarPeliculaPorId(Long id);

    List<PeliculaDTO> buscarPeliculaPorFiltro(String titulo, String genero, String orden);

    PeliculaDTO guardarPelicula(PeliculaDTO peliculaDTO);

    void agregarPersonajeAPelicula(Long peliculaId, Long personajeId);

    PeliculaDTO actualizarPelicula(Long id, PeliculaDTO movieNewData);

    void eliminarPelicula(Long id);

    void eliminarPersonajeDePelicula(Long peliculaId, Long personajeId);
}
