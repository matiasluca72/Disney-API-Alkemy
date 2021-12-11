package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class CharacterMapper {

    // Service
    private MovieService movieService;

    // Attribute Service loaded with @Lazy notation so it can handle BeanCurrentlyInCreationException
    @Autowired
    public CharacterMapper(@Lazy MovieService movieService) {
        this.movieService = movieService;
    }

    /**
     * Converts a List of CharacterEntity into a List of CharacterBasicDTO
     * @param entities a List of CharacterEntity to be converted
     * @return a List of CharacterBasicDTO
     */
    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {

        // Initializing the list to be returned
        List<CharacterBasicDTO> basicDTOS = new ArrayList();

        // For Each to convert each Entity from the list and add it to the new BasicDTO List
        for (CharacterEntity entity : entities) {
            basicDTOS.add(characterEntity2BasicDTO(entity));
        }
        return basicDTOS;
    }

    /**
     * Converts a List of CharacterEntity into a List of CharacterDTO
     * @param entities to be converted into a CharacterDTOs
     * @param loadMovies to determine if attribute "associatedMovies" should be loaded too
     * @return A List of CharacterDTO
     */
    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovies) {

        List<CharacterDTO> dtos = new ArrayList();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    /**
     * Converts an Object CharacterEntity into a CharacterBasicDTO
     * @param entity to be converted
     * @return its equivalent to a BasicDTO
     */
    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity) {

        CharacterBasicDTO basicDTO = new CharacterBasicDTO();

        basicDTO.setId(entity.getId());
        basicDTO.setImage(entity.getImage());
        basicDTO.setName(entity.getName());

        return basicDTO;
    }

    /**
     * Converts an Object CharacterDTO not persisted yet into a CharacterEntity
     * @param dto To be converted
     * @return its equivalent to a CharacterEntity
     */
    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {

        CharacterEntity entity = new CharacterEntity();

        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());

        return entity;
    }

    /**
     * Updates the CharacterEntity with the attributes inside the CharacterDTO. Then, returns the Entity updated
     * @param entity To be updated
     * @param dto with the new attributes to be set
     * @return The Entity already updated
     */
    public CharacterEntity updateCharacterDTO2Entity(CharacterEntity entity, CharacterDTO dto) {

        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());

        return entity;
    }

    /**
     * Converts a CharacterEntity into a CharacterDTO, optionally loading its associated Movies
     * @param entity To be converted
     * @param loadMovies indicates if associated movies should be loaded too
     * @return its equivalent to CharacterDTO
     */
    public CharacterDTO characterEntity2DTO(CharacterEntity entity, boolean loadMovies) {

        CharacterDTO dto = new CharacterDTO();

        dto.setId(entity.getId());
        dto.setName(entity.getName());
        dto.setImage(entity.getImage());
        dto.setAge(entity.getAge());
        dto.setWeight(entity.getWeight());
        dto.setStory(entity.getStory());
        if (loadMovies) {
            List<MovieDTO> movieDTOS = movieService.movieEntityList2DTOList(entity.getAssociatedMovies(), false);
            dto.setAssociatedMovies(movieDTOS);
        }
        return dto;
    }

    /**
     * Converts a List of CharacterDTO into a List of CharacterEntity
     * @param dtos to be converted into entities
     * @return their equivalent in CharacterEntity
     */
    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos) {

        List<CharacterEntity> entities = new ArrayList();
        for (CharacterDTO dto : dtos) {
            entities.add(characterDTO2Entity(dto));
        }
        return entities;
    }
}
