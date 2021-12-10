package com.alkemy.disneyapi.repository;

import com.alkemy.disneyapi.entity.MovieEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<MovieEntity, Long> {

    // Método para buscar a todos los que cumplan con icertas especificaciones o filtros
    List<MovieEntity> findAll(Specification<MovieEntity> spec);

}
