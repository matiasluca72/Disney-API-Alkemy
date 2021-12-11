package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.entity.GenreEntity;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component //Indicamos simplemente que este es un componente dentro de Spring
public class GenreMapper {

    /**
     * Converts an Object GenreDTO into a GenreEntity
     * @param dto genreDTO to be converted
     * @return genreEntity
     */
    public GenreEntity genreDTO2Entity(GenreDTO dto) {

        //Creamos una nueva instancia de la Entidad
        GenreEntity entity = new GenreEntity();

        //Setteamos los datos de la entidad con los datos del DTO
        entity.setName(dto.getName());
        entity.setImage(dto.getImage());

        //Devolvemos la entidad de tipo GenreEntity
        return entity;
    }

    /**
     * Converts an Object GenreEntity into a GenreDTO
     * @param entity genreEntity to be converted
     * @return genreDTO
     */
    public GenreDTO genreEntity2DTO(GenreEntity entity) {

        //Creamos una nueva instancia del DTO
        GenreDTO dto = new GenreDTO();

        //Setteamos los datos del DTO con los datos del Entity
        dto.setId(entity.getId()); //Ahora que la entidad persistida tiene un id, se lo seteamos al DTO
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());

        //Devolvemos la entidad de tipo GenreDTO
        return dto;
    }

    /**
     * Converts a List of GenreEntity into a List of GenreDTO
     * @param entities List of GenreEntity to be converted
     * @return A List of GenreDTO
     */
    public List<GenreDTO> genreEntityList2DTOList(List<GenreEntity> entities) {

        //Creamos la Lista donde guardaremos los DTOs
        List<GenreDTO> dtos = new ArrayList();

        /*For Each para recorrer la lista del parámetro y guardar cada Entity
        como un DTO usando el método de la propia clase para parsear de Entity 2 DTO */
        for (GenreEntity entity : entities) {
            dtos.add(genreEntity2DTO(entity));
        }

        //Devolvemos el listado ya completo
        return dtos;
    }
}
