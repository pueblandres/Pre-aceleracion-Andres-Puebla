package com.Alkemy.demo.servicios;

import com.Alkemy.demo.dto.GeneroDTO;

import java.util.List;

public interface GeneroServicio  {

    //POST
    GeneroDTO guardar(GeneroDTO dto);

    //GET
    List<GeneroDTO> todosLosGeneros();

    GeneroDTO buscarGeneroPorID(Long Id);

    //UPDATE
    GeneroDTO actualizarGenero(Long id, GeneroDTO genero);

    //DELETE
    void eliminarGenero(Long id);
}


