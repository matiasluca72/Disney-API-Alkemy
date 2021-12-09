package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    @Autowired
    private CharacterMapper characterMapper;
    @Autowired
    private CharacterService characterService;

    public MovieEntity movieDTO2Entity(MovieDTO dto) {

        MovieEntity entity = new MovieEntity();

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(string2LocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
        entity.setGenreId(dto.getGenreId());
        entity.setCharacters(
                characterService.look4OrCreate(
                        dto.getCharacters()
                )
        );
        return entity;
    }

    public MovieEntity updateMovieDTO2Entity(MovieEntity entity, MovieDTO dto) {

        entity.setImage(dto.getImage());
        entity.setTitle(dto.getTitle());
        entity.setCreationDate(string2LocalDate(dto.getCreationDate()));
        entity.setRating(dto.getRating());
        entity.setGenreId(dto.getGenreId());
        entity.setCharacters(
                characterService.look4OrCreate(
                        dto.getCharacters()
                )
        );
        return entity;
    }

    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    public MovieDTO movieEntity2DTO(MovieEntity entity) {

        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
        dto.setGenreId(entity.getGenreId());
        dto.setCharacters(characterMapper.characterEntityList2DTOList(entity.getCharacters()));

        return dto;
    }

    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities) {

        List<MovieBasicDTO> basicDTOS = new ArrayList();
        for (MovieEntity entity : entities) {
            basicDTOS.add(movieEntity2BasicDTO(entity));
        }
        return basicDTOS;

    }

    private MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity) {

        MovieBasicDTO basicDTO = new MovieBasicDTO();
        basicDTO.setId(entity.getId());
        basicDTO.setImage(entity.getImage());
        basicDTO.setTitle(entity.getTitle());
        basicDTO.setCreationDate(entity.getCreationDate().toString());
        return basicDTO;

    }

    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities) {

        List<MovieDTO> dtos = new ArrayList();
        for (MovieEntity entity : entities) {
            dtos.add(movieEntity2DTO(entity));
        }
        return dtos;
    }
}
