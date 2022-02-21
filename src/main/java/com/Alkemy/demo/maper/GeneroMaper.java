package com.Alkemy.demo.maper;

import com.Alkemy.demo.dto.GeneroDTO;
import com.Alkemy.demo.entidades.GeneroEntidad;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class GeneroMaper {

    public GeneroEntidad generoDTO2Entidad(GeneroDTO dto){

        GeneroEntidad generoEntidad = new GeneroEntidad();
        generoEntidad.setNombre(dto.getNombre());
        generoEntidad.setImagen(dto.getImagen());
        return generoEntidad;
    }

    public GeneroDTO generoEntidad2DTO(GeneroEntidad entidad){

        GeneroDTO dto = new GeneroDTO();
        dto.setId(entidad.getId());
        dto.setNombre(entidad.getNombre());
        dto.setImagen(entidad.getImagen());
        return dto;
    }

    public List<GeneroDTO> generoEntidadList2DTOList(List<GeneroEntidad> GeneroEntidadList) {
        List<GeneroDTO> generoDTOList = new ArrayList<>();

        for (GeneroEntidad entidad : GeneroEntidadList) {
            generoDTOList.add(this.generoEntidad2DTO(entidad));
        }
        return generoDTOList ;
    }


    public void generoEntidadActualizar(GeneroEntidad entidad, GeneroDTO dto) {
        entidad.setNombre(dto.getNombre());
        entidad.setImagen(dto.getImagen());

    }
}
