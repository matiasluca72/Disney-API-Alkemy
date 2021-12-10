package com.alkemy.disneyapi.service;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.exceptions.NotFoundException;

import java.util.List;

public interface MovieService {
    MovieDTO save(MovieDTO dto);

    List<MovieBasicDTO> getBasicDTOList();

    List<MovieDTO> getDTOList();

    void delete(Long id);

    MovieDTO update(Long id, MovieDTO dto) throws NotFoundException;

    List<MovieDTO> getByFilters(String title, Long idGenre, String order);

    List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> associatedMovies, boolean loadMovies);
}
