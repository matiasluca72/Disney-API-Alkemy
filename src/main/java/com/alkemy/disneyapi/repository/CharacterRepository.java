package com.alkemy.disneyapi.repository;

import com.alkemy.disneyapi.entity.CharacterEntity;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CharacterRepository extends JpaRepository<CharacterEntity, Long> {

    // Método para buscarlos a todos los que cumplan con ciertas especificaciones o filtros
    List<CharacterEntity> findAll(Specification<CharacterEntity> spec);

    // TODO: Not working properly, try to figure it out but it keeps throwing exception
    // jpa java.lang.IllegalArgumentException: Validation failed for query for method public abstract
    // Buscar a un personaje por su nombre
    /*@Query("SELECT c FROM Characters AS c WHERE c.name = :name")
    CharacterEntity findByName(@Param("name") String name);*/

}
