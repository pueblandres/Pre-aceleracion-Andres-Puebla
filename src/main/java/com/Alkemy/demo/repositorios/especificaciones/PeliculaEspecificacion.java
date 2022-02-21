package com.Alkemy.demo.repositorios.especificaciones;
import com.Alkemy.demo.dto.PeliculaFiltroDTO;
import com.Alkemy.demo.entidades.PeliculaEntidad;
import org.springframework.data.jpa.domain.Specification;

import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

import org.springframework.stereotype.Component;

@Component
public class PeliculaEspecificacion {

    public Specification<PeliculaEntidad> buscarPorFiltro(PeliculaFiltroDTO peliculaFiltroDTO) {

        return (root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();

            if(StringUtils.hasLength(peliculaFiltroDTO.getTitulo())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("titulo")),
                                "%" + peliculaFiltroDTO.getTitulo().toLowerCase() + "%"
                        )
                );
            }

            if(StringUtils.hasLength(peliculaFiltroDTO.getGenero())) {
                Long generoId = Long.parseLong(peliculaFiltroDTO.getGenero());
                predicates.add(
                        criteriaBuilder.equal(root.get("generoId"), generoId)
                );
            }

            query.distinct(true);

            String ordenPorFiltro = "titulo";
            query.orderBy(
                   peliculaFiltroDTO.ordenASC() ?
                            criteriaBuilder.asc(root.get(ordenPorFiltro )) :
                            criteriaBuilder.desc(root.get(ordenPorFiltro ))
            );

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
