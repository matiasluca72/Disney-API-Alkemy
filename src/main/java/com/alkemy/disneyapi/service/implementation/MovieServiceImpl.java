package com.alkemy.disneyapi.service.implementation;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.mapper.MovieMapper;
import com.alkemy.disneyapi.repository.MovieRepository;
import com.alkemy.disneyapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    @Autowired
    private MovieRepository movieRepository;
    @Autowired
    private MovieMapper movieMapper;

    public MovieDTO save(MovieDTO dto) {

        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved);

        return result;
    }

    public List<MovieBasicDTO> getBasicDTOList() {

        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieBasicDTO> result = movieMapper.movieEntityList2BasicDTOList(movies);
        return result;
    }

    public List<MovieDTO> getDTOList() {

        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> dtos = movieMapper.movieEntityList2DTOList(entities);
        return dtos;
    }

    public void delete(Long id) {
        movieRepository.deleteById(id);
    }

    public MovieDTO update(Long id, MovieDTO dto) throws NotFoundException {

        Optional<MovieEntity> result = movieRepository.findById(id);
        if (result.isPresent()) {
            MovieEntity entity = movieMapper.updateMovieDTO2Entity(result.get(), dto);
            MovieEntity entityUpdated = movieRepository.save(entity);
            MovieDTO dtoUpdated = movieMapper.movieEntity2DTO(entityUpdated);
            return dtoUpdated;
        } else {
            throw new NotFoundException("Requested movie was not found.");
        }
    }
}
