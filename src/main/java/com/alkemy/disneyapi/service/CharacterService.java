package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    /**
     * Gets all the Characters saved in DB and converts them into Basic DTO format (id, image and name)
     * @return List of all Characters in DB with their basic attributes
     */
    List<CharacterBasicDTO> getBasicDTOList();

    /**
     * Gets all Characters saved in DB and converts them into DTO Objects in a List
     * @return A List of Characters in DTO format with all their attributes
     */
    List<CharacterDTO> getDTOList();

    /**
     * Saves the CharacterDTO received to the DB and returns it with its brand-new id
     * @param dto CharacterDTO with all their attributes setted
     * @return The same CharacterDTO with its id setted
     */
    CharacterDTO save(CharacterDTO dto);

    /**
     * Performs a logic delete to the entity related to the received id
     * @param id Of the entity to delete
     */
    void delete(Long id);

    /**
     * Updates the attributes of the Entity related to the received id and sets the new attributes from the DTO received
     * @param id Of the entity to update
     * @param dto with the updated attributes
     * @return The DTO with its attributes updated
     * @throws NotFoundException
     */
    CharacterDTO update(Long id, CharacterDTO dto) throws NotFoundException;

    /**
     * Returns a List of CharacterDTOs that meet the received filters
     * @param name Filter by name
     * @param age Filter by age
     * @param idMovies Filter by Associated Movies
     * @return A List of CharacterDTOs which met the filters
     */
    List<CharacterDTO> getByFilters(String name, Byte age, Set<Long> idMovies);

    /**
     * Method which looks for Characters by name. If there is such character, it adds it to the List of CharacterEntitys
     * if there is not, then it creates a new one
     *
     * @param dtos A list of CharacterDTOs
     * @return A list of Character Entitites
     */
    List<CharacterEntity> look4OrCreate(List<CharacterDTO> dtos);

    /**
     * Turns a List of Entities into a List of DTOs
     * @param characters to be converted to a List of DTOs
     * @param loadMovies determines if Associated Movies should be loaded or not
     * @return A List of CharacterDTOs
     */
    List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characters, boolean loadMovies);
}
