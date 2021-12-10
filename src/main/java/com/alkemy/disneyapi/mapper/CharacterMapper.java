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

    private MovieService movieService;

    @Autowired
    public CharacterMapper(@Lazy MovieService movieService) {
        this.movieService = movieService;
    }

    public List<CharacterBasicDTO> characterEntityList2BasicDTOList(List<CharacterEntity> entities) {

        List<CharacterBasicDTO> basicDTOS = new ArrayList();

        for (CharacterEntity entity : entities) {
            basicDTOS.add(characterEntity2BasicDTO(entity));
        }
        return basicDTOS;
    }

    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> entities, boolean loadMovies) {

        List<CharacterDTO> dtos = new ArrayList();
        for (CharacterEntity entity : entities) {
            dtos.add(characterEntity2DTO(entity, loadMovies));
        }
        return dtos;
    }

    public CharacterBasicDTO characterEntity2BasicDTO(CharacterEntity entity) {

        CharacterBasicDTO basicDTO = new CharacterBasicDTO();

        basicDTO.setImage(entity.getImage());
        basicDTO.setName(entity.getName());

        return basicDTO;
    }

    public CharacterEntity characterDTO2Entity(CharacterDTO dto) {

        CharacterEntity entity = new CharacterEntity();

        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());

        return entity;
    }

    public CharacterEntity updateCharacterDTO2Entity(CharacterEntity entity, CharacterDTO dto) {

        entity.setName(dto.getName());
        entity.setImage(dto.getImage());
        entity.setAge(dto.getAge());
        entity.setWeight(dto.getWeight());
        entity.setStory(dto.getStory());

        return entity;
    }

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


    public List<CharacterEntity> characterDTOList2EntityList(List<CharacterDTO> dtos) {

        List<CharacterEntity> entities = new ArrayList();
        for (CharacterDTO dto : dtos) {
            entities.add(characterDTO2Entity(dto));
        }
        return entities;
    }
}