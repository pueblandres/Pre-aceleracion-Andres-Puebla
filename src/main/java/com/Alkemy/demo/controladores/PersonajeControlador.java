package com.Alkemy.demo.controladores;

import com.Alkemy.demo.dto.PersonajeBasicoDTO;
import com.Alkemy.demo.dto.PersonajeDTO;
import com.Alkemy.demo.servicios.PersonajeServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("personajes")
public class PersonajeControlador {
    @Autowired
    private PersonajeServicio personajeServicio;


    @GetMapping
    public ResponseEntity<List<PersonajeBasicoDTO>> personajesBasicos() {
        List<PersonajeBasicoDTO> personajesBasicos = personajeServicio.personajesBasicos();
        return ResponseEntity.ok(personajesBasicos);
    }


    @PostMapping
    public ResponseEntity<PersonajeDTO> guardarPersonaje(@RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO guardarPersonaje = personajeServicio.guardarPersonaje(personajeDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(guardarPersonaje);
    }


    @PutMapping("/{id}")
    public ResponseEntity<PersonajeDTO> actualizarPersonaje(@PathVariable Long id, @RequestBody PersonajeDTO personajeDTO) {
        PersonajeDTO actualizarPersonaje = personajeServicio.actualizarPersonaje(id, personajeDTO);
        return ResponseEntity.ok().body(actualizarPersonaje);
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> eliminarPersonaje(@PathVariable Long id) {
        personajeServicio.eliminarPersonaje(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }


    @GetMapping("/{id}")
    public ResponseEntity<PersonajeDTO> buscarPersonajePorID(@PathVariable Long id) {
        PersonajeDTO personajeDTO = personajeServicio.buscarPersonajePorID(id);
        return ResponseEntity.ok(personajeDTO);
    }


    @GetMapping("/filtros")
    public ResponseEntity<List<PersonajeDTO>> buscarPersonajesPorFiltro(
            @RequestParam (required = false) String nombre,
            @RequestParam (required = false) Integer edad,
            @RequestParam (required = false) Set<Long> peliculas
    ) {
        List<PersonajeDTO> personajes = personajeServicio.buscarPersonajesPorFiltro(nombre, edad, peliculas);
        return ResponseEntity.ok(personajes);
    }
}
