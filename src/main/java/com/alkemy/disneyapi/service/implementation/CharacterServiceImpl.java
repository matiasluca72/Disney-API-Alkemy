package com.alkemy.disneyapi.service.implementation;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.dto.CharacterFiltersDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.mapper.CharacterMapper;
import com.alkemy.disneyapi.repository.CharacterRepository;
import com.alkemy.disneyapi.repository.specifications.CharacterSpecification;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.swing.text.html.Option;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Service
public class CharacterServiceImpl implements CharacterService {

    // Repo
    @Autowired
    private CharacterRepository characterRepository;
    @Autowired
    private CharacterSpecification characterSpecification;
    // Mapper
    @Autowired
    private CharacterMapper characterMapper;

    public List<CharacterBasicDTO> getBasicDTOList() {

        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterBasicDTO> result = characterMapper.characterEntityList2BasicDTOList(characters);
        return result;
    }

    public List<CharacterDTO> getDTOList() {

        List<CharacterEntity> characters = characterRepository.findAll();
        List<CharacterDTO> result = characterMapper.characterEntityList2DTOList(characters);
        return result;
    }

    public CharacterDTO save(CharacterDTO dto) {

        CharacterEntity entity = characterMapper.characterDTO2Entity(dto);
        CharacterEntity entitySaved = characterRepository.save(entity);
        CharacterDTO result = characterMapper.characterEntity2DTO(entitySaved);

        return result;
    }

    public void delete(Long id) {
        characterRepository.deleteById(id);
    }

    public CharacterDTO update(Long id, CharacterDTO dto) throws NotFoundException {

        Optional<CharacterEntity> result = characterRepository.findById(id);
        if (result.isPresent()) {
            CharacterEntity entity = result.get();

            entity.setName(dto.getName());
            entity.setImage(dto.getImage());
            entity.setAge(dto.getAge());
            entity.setWeight(dto.getWeight());
            entity.setStory(dto.getStory());

            CharacterEntity entityUpdated = characterRepository.save(entity);
            CharacterDTO dtoUpdated = characterMapper.characterEntity2DTO(entityUpdated);
            return dtoUpdated;
        } else {
            throw new NotFoundException("Requested character was not found.");
        }
    }

    public List<CharacterDTO> getByFilters(String name, Byte age, Set<Long> idMovies) {

        CharacterFiltersDTO filtersDTO = new CharacterFiltersDTO(name, age, idMovies);
        List<CharacterEntity> entities = characterRepository.findAll(characterSpecification.getByFilters(filtersDTO));
        List<CharacterDTO> dtos = characterMapper.characterEntityList2DTOList(entities);
        return dtos;
    }

    /**
     * Method which looks for Characters by name and if there is such character, it adds it to the List of CharacterEntitys
     * if there is not, then it creates a new one
     *
     * @param dtos A list of CharacterDTOs
     * @return A list of Character Entitites
     */
    public List<CharacterEntity> look4OrCreate(List<CharacterDTO> dtos) {

        List<CharacterEntity> entities = new ArrayList();
        CharacterEntity entity;
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

}
