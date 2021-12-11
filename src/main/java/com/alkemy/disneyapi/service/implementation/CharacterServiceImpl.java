package com.alkemy.disneyapi.service.implementation;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.CharacterFiltersDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.exceptions.InvalidDTOException;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.mapper.CharacterMapper;
import com.alkemy.disneyapi.repository.CharacterRepository;
import com.alkemy.disneyapi.repository.specifications.CharacterSpecification;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    // Repo
    private CharacterRepository characterRepository;
    private CharacterSpecification characterSpecification;
    // Mapper
    private CharacterMapper characterMapper;

    // Setter/Field Injection of Dependencies so we can handle BeanCurrentlyInCreationException
    @Autowired
    public void setCharacterRepository(CharacterRepository characterRepository, CharacterSpecification characterSpecification, CharacterMapper characterMapper) {
        this.characterRepository = characterRepository;
        this.characterSpecification = characterSpecification;
        this.characterMapper = characterMapper;
    }

    /**
     * Gets all the Characters saved in DB and converts them into Basic DTO format (id, image and name)
     * @return List of all Characters in DB with their basic attributes
     */
    public List<CharacterBasicDTO> getBasicDTOList() {

        //Gets all the Entities from DB and then turn them into a List of BasicDTOs
        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2BasicDTOList(characters);
        return result;
    }

    /**
     * Gets all Characters saved in DB and converts them into DTO Objects in a List
     * @return A List of Characters in DTO format with all their attributes
     */
    public List<CharacterDTO> getDTOList() {

        //Gets all the Entities from DB and then turn them into a List of DTOs
        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(characters, true);
        return result;
    }

    /**
     * Saves the CharacterDTO received to the DB and returns it with its brand-new id
     * @param dto CharacterDTO with all their attributes setted
     * @return The same CharacterDTO with its id setted
     */
    public CharacterDTO save(CharacterDTO dto) {

        //Verifies if the DTO has all the attributes well setted
        validation(dto);

        // Turns the received DTO into Entity, saves it and turns it again into DTO with an ID
        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved, false);

        return result;
    }

    /**
     * Performs a logic delete to the entity related to the received id
     * @param id Of the entity to delete
     */
    public void delete(Long id) {

        if (characterRepository.findById(id) == null)
            throw new NotFoundException("The id does not correspond to any Character in the Database.");
        characterRepository.deleteById(id);
    }

    /**
     * Updates the attributes of the Entity related to the received id and sets the new attributes from the DTO received
     * @param id Of the entity to update
     * @param dto with the updated attributes
     * @return The DTO with its attributes updated
     * @throws NotFoundException
     */
    public CharacterDTO update(Long id, CharacterDTO dto) throws NotFoundException {

        //Validation of new attributes
        validation(dto);

        //Looks for the Entity to update, sets the new attributes, saves it and returns it as DTO
        Optional<CharacterEntity> result = characterRepository.findById(id);
        if (result.isPresent()) {
            CharacterEntity entity = characterMapper.updateCharacterDTO2Entity(result.get(), dto);
            CharacterEntity entityUpdated = characterRepository.save(entity);
            CharacterDTO dtoUpdated = characterMapper.characterEntity2DTO(entityUpdated, false);
            return dtoUpdated;
        } else {
            throw new NotFoundException("Requested character was not found.");
        }
    }

    /**
     * Returns a List of CharacterDTOs that met the received filters
     * @param name Filter by name
     * @param age Filter by age
     * @param idMovies Filter by Associated Movies
     * @return A List of CharacterDTOs which met the filters
     */
    public List<CharacterDTO> getByFilters(String name, Byte age, Set<Long> idMovies) {

        /* Creates a new FiltersDTO with the received parameters, looks for the entities which met the specificacions
        at Database and turns the List into a DTOList */
        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, idMovies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntityList2DTOList(entities, true);
        return dtos;
    }

    /**
     * Method which looks for Characters by name. If there is such character, it adds it to the List of CharacterEntitys
     * if there is not, then it creates a new one
     *
     * @param dtos A list of CharacterDTOs
     * @return A list of Character Entitites
     */
    public List<CharacterEntity> look4OrCreate(List<CharacterDTO> dtos) {

        /* Verifies id of each DTO. If it has a value, verifies if it exists in the DataBase.
        * If it does, it's added to the List. Else, it will be created and added using the Mapper. */
        List<CharacterEntity> entities = new ArrayList();
        for (CharacterDTO dto : dtos) {
            if (dto.getId() != null) {
                Optional<CharacterEntity> result = characterRepository.findById(dto.getId());
                if (result.isPresent()) {
                    entities.add(result.get());
                } else {
                    entities.add(characterMapper.characterDTO2Entity(dto));
                }
            } else {
                entities.add(characterMapper.characterDTO2Entity(dto));
            }
        }
        return entities;
    }

    /**
     * Turns a List of Entities into a List of DTOs
     * @param characters to be converted to a List of DTOs
     * @param loadMovies determines if Associated Movies should be loaded or not
     * @return A List of CharacterDTOs
     */
    public List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characters, boolean loadMovies) {
        return characterMapper.characterEntityList2DTOList(characters, loadMovies);
    }

    /**
     * Validates all attributes from a received DTO and throw an Exception if there is any invalid attribute
     * @param dto The Object to be validated
     */
    private void validation(CharacterDTO dto) {
        if (dto == null)
            throw new InvalidDTOException("Character cannot be null");
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new InvalidDTOException("Character name cannot be empty or null");
        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new InvalidDTOException("Character image cannot be empty or null");
        if (dto.getStory() == null || dto.getStory().isEmpty())
            throw new InvalidDTOException("Character story cannot be empty or null");
        if (dto.getAge() == null)
            throw new InvalidDTOException("Character age cannot be null");
        if (dto.getWeight() == null)
            throw new InvalidDTOException("Character weight cannot be null");
    }
}
