package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.exceptions.NotFoundException;

import java.util.List;

public interface MovieService {

    /**
     * Saves the DTO received and returns it with its brand-new id
     * @param dto to be persisted
     * @return The same dto with its brand-new id
     */
    MovieDTO save(MovieDTO dto);

    /**
     * Gets a List of all Movies from Database as Basic DTO
     * @return a List of all Movies as BasicDTO
     */
    List<MovieBasicDTO> getBasicDTOList();

    /**
     * Gets a List of all Movies from Database as DTO
     * @return a List of all Movies as DTO
     */
    List<MovieDTO> getDTOList();

    /**
     * Performs a logic delete to the Entity related to the received id
     * @param id of the entity to be deleted
     */
    void delete(Long id);

    /**
     * Updates an Entity related to the received id with the new attributes from the received dto
     * @param id of the entity to be updated
     * @param dto with all the new attributes
     * @return The Entity as DTO with its updated attributes
     * @throws NotFoundException
     */
    MovieDTO update(Long id, MovieDTO dto) throws NotFoundException;

    /**
     * Returns a List of MoviesDTOs that met the received filters
     * @param title Filter by title
     * @param idGenre Filter by Genre
     * @param order Order by CreationDate (ASC / DESC)
     * @return A List of MoviesDTO according to the specifications
     */
    List<MovieDTO> getByFilters(String title, Long idGenre, String order);

    /**
     * Turns the received List of Entities into a List of DTOs
     * @param associatedMovies Movies to be turned into a List of DTOs
     * @param loadCharacters Boolean to determine if characters should be loaded
     * @return A List of MoviesDTO
     */
    List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> associatedMovies, boolean loadCharacters);
}
