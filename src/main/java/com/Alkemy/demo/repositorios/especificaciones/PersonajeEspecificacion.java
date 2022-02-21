package com.Alkemy.demo.repositorios.especificaciones;

import com.Alkemy.demo.dto.PersonajeFiltroDTO;
import com.Alkemy.demo.entidades.PeliculaEntidad;
import com.Alkemy.demo.entidades.PersonajeEntidad;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Expression;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.Predicate;

@Component
public class PersonajeEspecificacion {

    public Specification<PersonajeEntidad> buscarPersonajesPorFiltro(PersonajeFiltroDTO filtroDTO) {

        return ((root, query, criteriaBuilder) -> {
            List<Predicate> predicates = new ArrayList<>();
            if (StringUtils.hasLength(filtroDTO.getNombre())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("nombre")),
                                "%" + filtroDTO.getNombre().toLowerCase() + "%"
                        )
                );
            }

            if (filtroDTO.getEdad() != null) {
                predicates.add(
                        criteriaBuilder.equal(root.get("edad"), filtroDTO.getEdad()
                ));
            }


            if(!CollectionUtils.isEmpty(filtroDTO.getPeliculas())) {
                Join<PersonajeEntidad, PeliculaEntidad> join = root.join("peliculas", JoinType.INNER);
                Expression<String> peliculasId = join.get("id");
                predicates.add(peliculasId.in(filtroDTO.getPeliculas()));
            }

            query.distinct(true);


            query.orderBy(criteriaBuilder.asc(root.get("nombre")));

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        });
    }
}
