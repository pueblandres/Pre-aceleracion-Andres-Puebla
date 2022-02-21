package com.Alkemy.demo.controladores;

import com.Alkemy.demo.dto.PeliculaBasicoDTO;
import com.Alkemy.demo.dto.PeliculaDTO;
import com.Alkemy.demo.servicios.PeliculaServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("peliculas")
public class PeliculaControlador {

    @Autowired
    private PeliculaServicio peliculaServicio;


    @GetMapping
    public ResponseEntity<List<PeliculaBasicoDTO>> buscarPeliculasBasico() {
        List<PeliculaBasicoDTO> peliculas = peliculaServicio.buscarPeliculasBasico();
        return ResponseEntity.ok(peliculas);
    }


    @GetMapping("/{id}")
    public ResponseEntity<PeliculaDTO> buscarPelicula(@PathVariable Long id) {
        PeliculaDTO peliculaDTO = peliculaServicio.buscarPeliculaPorId(id);
        return ResponseEntity.ok(peliculaDTO);
    }


    @PostMapping
    public ResponseEntity<PeliculaDTO> guardarPelicula(@RequestBody PeliculaDTO peliculaDTO) {
        PeliculaDTO guardarPelicula = peliculaServicio.guardarPelicula(peliculaDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardarPelicula);
    }


    @PostMapping("/{peliculaId}/personaje/{personajeId}")
    public ResponseEntity<Void> guardarPersonajeAPelicula(@PathVariable Long peliculaId, @PathVariable Long personajeId) {
        peliculaServicio.agregarPersonajeAPelicula(peliculaId, personajeId);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }


    @PutMapping("/{id}")
    public ResponseEntity<PeliculaDTO> actualizarPelicula(@PathVariable Long id, @RequestBody PeliculaDTO pelicula) {
        PeliculaDTO peliculaDTO = peliculaServicio.actualizarPelicula(id, pelicula);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(peliculaDTO);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPelicula(@PathVariable Long id) {
        peliculaServicio.eliminarPelicula(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @DeleteMapping("/{peliculaId}/personaje/{personajeId}")
    public ResponseEntity<Void> eliminarPersonajeDePelicula(@PathVariable Long peliculaId, @PathVariable Long personajeId) {
        peliculaServicio.eliminarPersonajeDePelicula(peliculaId, personajeId);
        return ResponseEntity.status(HttpStatus.ACCEPTED).build();
    }


    @GetMapping("/filtros")
    public ResponseEntity<List<PeliculaDTO>> buscarPeliculaPorFiltro (
            @RequestParam(required = false) String titulo,
            @RequestParam(required = false) String genero,
            @RequestParam(required = false, defaultValue = "ASC") String orden
    )
    { List<PeliculaDTO> filtroPeliculas = peliculaServicio.buscarPeliculaPorFiltro(titulo, genero, orden);
        return ResponseEntity.ok(filtroPeliculas);
    }

}
