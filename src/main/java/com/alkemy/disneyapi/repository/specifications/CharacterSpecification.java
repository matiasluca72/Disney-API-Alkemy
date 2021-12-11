package com.alkemy.disneyapi.repository.specifications;

import com.alkemy.disneyapi.dto.CharacterFiltersDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Component;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.criteria.Expression;
import javax.persistence.criteria.Join;
import javax.persistence.criteria.JoinType;
import javax.persistence.criteria.Predicate;
import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterSpecification {

    /**
     * Method for building dinamic queries for the DataBase according to the Filters received
     * @param filtersDTO To be applied to the query
     * @return A Specification of CharacterEntity type
     */
    public Specification<CharacterEntity> getByFilters(CharacterFiltersDTO filtersDTO) {
        return (root, query, criteriaBuilder) -> {

            List<Predicate> predicates = new ArrayList();

            // Adding name specificaction
            if (StringUtils.hasLength(filtersDTO.getName())) {
                predicates.add(
                        criteriaBuilder.like(
                                criteriaBuilder.lower(root.get("name")),
                                "%" + filtersDTO.getName().toLowerCase() + "%"
                        )
                );
            }

            // Adding age specificaction
            if (filtersDTO.getAge() != null) {
                predicates.add(
                        criteriaBuilder.equal((root.get("age")),
                                filtersDTO.getAge()
                        )

                );
            }

            // Adding Movies specificaction
            if (!CollectionUtils.isEmpty(filtersDTO.getIdMovies())) {
                Join<MovieEntity, CharacterEntity> join = root.join("associatedMovies", JoinType.INNER);
                Expression<String> moviesId = join.get("id");
                predicates.add(moviesId.in(filtersDTO.getIdMovies()));
            }

            //Remove duplicates
            query.distinct(true);

            return criteriaBuilder.and(predicates.toArray(new Predicate[0]));
        };
    }
}
