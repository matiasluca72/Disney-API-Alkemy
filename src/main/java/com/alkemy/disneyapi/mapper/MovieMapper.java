package com.alkemy.disneyapi.mapper;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Component
public class MovieMapper {

    // Service
    private CharacterService characterService;

    // Attribute Service set with @Lazy notation in order to handle BeanCurrentlyInCreationException
    @Autowired
    public MovieMapper(@Lazy CharacterService characterService) {
        this.characterService = characterService;
    }

    /**
     * Converts a MovieDTO into a MovieEntity
     * @param dto to be converted
     * @return The dto received as an Entity
     */
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

    /**
     * Updates the MovieEntity received with the attributes set in MovieDTO
     * @param entity to be updated
     * @param dto with the new attributes
     * @return the Entity already updated
     */
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

    /**
     * Util which converts a Date as a String with format "yyyy-MM-dd" into a LocalDate Object
     * @param stringDate the date using a "yyyy-MM-dd" format
     * @return The same date as LocalDate Object
     */
    private LocalDate string2LocalDate(String stringDate) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate date = LocalDate.parse(stringDate, formatter);
        return date;
    }

    /**
     * Converts a MovieEntity into a MovieDTO, optionally setting its characters too
     * @param entity to be converted
     * @param loadCharacters indicates if attribute 'characters' should be loaded
     * @return the Object as DTO
     */
    public MovieDTO movieEntity2DTO(MovieEntity entity, boolean loadCharacters) {

        MovieDTO dto = new MovieDTO();

        dto.setId(entity.getId());
        dto.setImage(entity.getImage());
        dto.setTitle(entity.getTitle());
        dto.setCreationDate(entity.getCreationDate().toString());
        dto.setRating(entity.getRating());
        dto.setGenreId(entity.getGenreId());
        if (loadCharacters) {
            dto.setCharacters(characterService.characterEntityList2DTOList(entity.getCharacters(), false));
        };
        return dto;
    }

    /**
     * Converts a List of MovieEntity into a List of MovieBasicDTO
     * @param entities to be converted
     * @return a List of MovieBasicDTO
     */
    public List<MovieBasicDTO> movieEntityList2BasicDTOList(List<MovieEntity> entities) {

        List<MovieBasicDTO> basicDTOS = new ArrayList();
        for (MovieEntity entity : entities) {
            basicDTOS.add(movieEntity2BasicDTO(entity));
        }
        return basicDTOS;
    }

    /**
     * Converts a MovieEntity into a MovieBasicDTO
     * @param entity to be converted
     * @return its equivalent to MovieBasicDTO
     */
    private MovieBasicDTO movieEntity2BasicDTO(MovieEntity entity) {

        MovieBasicDTO basicDTO = new MovieBasicDTO();
        basicDTO.setId(entity.getId());
        basicDTO.setImage(entity.getImage());
        basicDTO.setTitle(entity.getTitle());
        basicDTO.setCreationDate(entity.getCreationDate().toString());
        return basicDTO;
    }

    /**
     * Converts a List of MovieEntity into a List of MovieDTO, optionally loading its characters too
     * @param entities to be converted
     * @param loadCharacters indicates if attribute 'characters' should be loaded or not
     * @return a List with MovieDTOs
     */
    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> entities, boolean loadCharacters) {

        List<MovieDTO> dtos = new ArrayList();
        for (MovieEntity entity : entities) {
            dtos.add(movieEntity2DTO(entity, loadCharacters));
        }
        return dtos;
    }
}
