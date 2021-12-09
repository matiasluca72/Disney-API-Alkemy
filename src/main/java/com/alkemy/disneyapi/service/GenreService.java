package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.exceptions.NotFoundException;

import java.util.List;

public interface GenreService {

    //Método abstracto para persistir un DTO
    GenreDTO save(GenreDTO dto);

    //Método abstracto para devolver todas las entidades de la DB en forma de Lista de DTOs
    List<GenreDTO> getAllGenres();

    //Método abstracto para eliminar un registro que corresponda a este id
    void delete(Long id);

    GenreDTO update(Long id, GenreDTO dto) throws NotFoundException;
}
