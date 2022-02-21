package com.Alkemy.demo.controladores;

import com.Alkemy.demo.dto.GeneroDTO;
import com.Alkemy.demo.servicios.GeneroServicio;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("generos")
public class GeneroControlador {

    @Autowired
    private GeneroServicio generoServicio;


    @PostMapping
    public ResponseEntity<GeneroDTO> guardar(@RequestBody GeneroDTO genero){

        GeneroDTO generoGuardado = generoServicio.guardar(genero);

        return ResponseEntity.status(HttpStatus.CREATED).body(generoGuardado);
    }


    @GetMapping
    public ResponseEntity<List<GeneroDTO>> todosLosGeneros() {
        List<GeneroDTO> generoDtoList = generoServicio.todosLosGeneros();
        return ResponseEntity.ok().body(generoDtoList);
    }


    @GetMapping("/{id}")
    public ResponseEntity<GeneroDTO> buscarGeneroPorID(@PathVariable Long id) {
        GeneroDTO generoDto =generoServicio.buscarGeneroPorID(id);
        return ResponseEntity.ok().body(generoDto);
    }



    @PutMapping("{id}")
    public ResponseEntity<GeneroDTO> actualizarGenero(@PathVariable Long id, @RequestBody GeneroDTO genero){
        GeneroDTO generoDTO = generoServicio.actualizarGenero(id, genero);
        return ResponseEntity.ok().body(generoDTO);
    }


    @DeleteMapping("{id}")
    public ResponseEntity<Void> eliminarGenero(@PathVariable Long id) {
        generoServicio.eliminarGenero(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }



}
