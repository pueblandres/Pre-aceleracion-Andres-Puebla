package com.Alkemy.demo.servicios.impl;

import com.Alkemy.demo.dto.PeliculaBasicoDTO;
import com.Alkemy.demo.dto.PeliculaDTO;
import com.Alkemy.demo.dto.PeliculaFiltroDTO;
import com.Alkemy.demo.entidades.PeliculaEntidad;
import com.Alkemy.demo.entidades.PersonajeEntidad;
import com.Alkemy.demo.excepcion.ErrorServicio;
import com.Alkemy.demo.maper.PeliculaMaper;
import com.Alkemy.demo.maper.PersonajeMaper;
import com.Alkemy.demo.repositorios.PeliculaRepositorio;
import com.Alkemy.demo.repositorios.especificaciones.PeliculaEspecificacion;
import com.Alkemy.demo.servicios.PeliculaServicio;
import com.Alkemy.demo.servicios.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PeliculaServicioImpl implements PeliculaServicio {

    @Autowired
    private PeliculaRepositorio peliculaRepositorio;
    @Autowired
    private PeliculaMaper peliculaMaper;
    @Autowired
    private PersonajeServicio personajeServicio;
    @Autowired
    private PersonajeMaper personajeMaper;
    @Autowired
    private PeliculaEspecificacion peliculaEspecificacion;

       public List<PeliculaBasicoDTO> buscarPeliculasBasico() {
        List<PeliculaEntidad> entidades =peliculaRepositorio.findAll();
        return peliculaMaper.peliculaEntidadList2BasicoDTOList(entidades);
    }

    public PeliculaDTO buscarPeliculaPorId(Long id) {
        Optional<PeliculaEntidad> peliculaEntidad =peliculaRepositorio.findById(id);
        if (!peliculaEntidad.isPresent()) {
            throw new ErrorServicio("El id de pelicula no existe");
        }
        return peliculaMaper.peliculaEntidad2DTO(peliculaEntidad.get(), true);
    }


    public PeliculaDTO guardarPelicula (PeliculaDTO peliculaDTO) {
        PeliculaEntidad peliculaEntidad = peliculaMaper.peliculaDTO2Entidad(peliculaDTO);
        PeliculaEntidad entidadGuardada =peliculaRepositorio.save(peliculaEntidad);
        return peliculaMaper.peliculaEntidad2DTO(entidadGuardada, true);
    }


    public PeliculaDTO actualizarPelicula(Long id, PeliculaDTO pelicula) {

        Optional<PeliculaEntidad> entidad = peliculaRepositorio.findById(id);
        if (!entidad.isPresent()) {
            throw new ErrorServicio("El id de pelicula no existe");
        }
        this.peliculaMaper.peliculaEntidadActualizar(entidad.get(), pelicula);
        PeliculaEntidad guardarEntidad = peliculaRepositorio.save(entidad.get());
        return this.peliculaMaper.peliculaEntidad2DTO(guardarEntidad, false);

    }


    public void eliminarPelicula(Long id) {
        if (peliculaRepositorio.existsById(id)) {
            peliculaRepositorio.deleteById(id);
        } else {
            throw new ErrorServicio("El id de pelicula no existe");
        }
    }

    public void eliminarPersonajeDePelicula(Long peliculaId, Long personajeID) {
        PeliculaEntidad pelicula = getPeliculaEntidadById(peliculaId);
        pelicula.getPersonajes().size();
        PersonajeEntidad personajeEntidad = personajeServicio.buscarEntidadPorID(personajeID);
        pelicula.eliminarPersonaje(personajeEntidad);
        peliculaRepositorio.save(pelicula);
    }


    public List<PeliculaDTO> buscarPeliculaPorFiltro(String titulo, String genero, String orden) {
        PeliculaFiltroDTO peliculaFiltroDTO= new PeliculaFiltroDTO(titulo, genero, orden);
        List<PeliculaEntidad> peliculaEntidad =peliculaRepositorio.findAll(peliculaEspecificacion.buscarPorFiltro(peliculaFiltroDTO));
        return peliculaMaper.peliculaEntidadList2DTOList(peliculaEntidad, true);
    }


    public PeliculaEntidad getPeliculaEntidadById(Long peliculaId) {
        Optional<PeliculaEntidad> pelicula =peliculaRepositorio.findById(peliculaId);
        if (!pelicula.isPresent()) {
            throw new ErrorServicio("El id de pelicula no existe");
        }
        return pelicula.get();
    }

    public void agregarPersonajeAPelicula(Long peliculaId, Long personajeId) {
        PeliculaEntidad pelicula = this.getPeliculaEntidadById(peliculaId);
        pelicula.getPersonajes().size();
        PersonajeEntidad personajeEntidad = personajeServicio.buscarEntidadPorID(personajeId);
        pelicula.agregarPersonaje(personajeEntidad);peliculaRepositorio.save(pelicula);
    }

}
