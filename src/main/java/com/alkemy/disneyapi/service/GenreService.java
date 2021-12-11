package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.exceptions.NotFoundException;

import java.util.List;

public interface GenreService {

    /**
     * Saves the received DTO and returns it with a brand-new id
     * @param dto To be persisted
     * @return The same DTO with a brand-new id
     */
    GenreDTO save(GenreDTO dto);

    /**
     * Returns a List with all the genres in Database in DTO format
     * @return a List with all the genres in Database in DTO format
     */    List<GenreDTO> getAllGenres();

    /**
     * Performs a logic delete to the Entity related to the received id
     * @param id of the entity to be deleted
     */    void delete(Long id);

    /**
     * Updates attributes of the Entity related to the received id with the new attributes from the received DTO
     * @param id of the Entity to be updated
     * @param dto containing the new attributes
     * @return The Entity turned into DTO with its new attributes setted
     * @throws NotFoundException
     */
    GenreDTO update(Long id, GenreDTO dto) throws NotFoundException;
}
