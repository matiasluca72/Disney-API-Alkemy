package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.entity.CharacterEntity;
import com.alkemy.disneyapi.exceptions.NotFoundException;

import java.util.List;
import java.util.Set;

public interface CharacterService {

    List<CharacterBasicDTO> getBasicDTOList();

    List<CharacterDTO> getDTOList();

    CharacterDTO save(CharacterDTO dto);

    void delete(Long id);

    CharacterDTO update(Long id, CharacterDTO dto) throws NotFoundException;

    List<CharacterDTO> getByFilters(String name, Byte age, Set<Long> idMovies);

    List<CharacterEntity> look4OrCreate(List<CharacterDTO> dtos);

    List<CharacterDTO> characterEntityList2DTOList(List<CharacterEntity> characters, boolean loadMovies);
}
