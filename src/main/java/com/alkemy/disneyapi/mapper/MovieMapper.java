package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

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
}
