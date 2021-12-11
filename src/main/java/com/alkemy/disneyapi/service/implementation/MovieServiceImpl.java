package com.alkemy.disneyapi.service.implementation;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.dto.MovieFiltersDTO;
import com.alkemy.disneyapi.entity.MovieEntity;
import com.alkemy.disneyapi.exceptions.InvalidDTOException;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.mapper.MovieMapper;
import com.alkemy.disneyapi.repository.MovieRepository;
import com.alkemy.disneyapi.repository.specifications.MovieSpecification;
import com.alkemy.disneyapi.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class MovieServiceImpl implements MovieService {

    // Repository
    private MovieRepository movieRepository;
    private MovieSpecification movieSpecification;
    // Mapper
    private MovieMapper movieMapper;

    // Setter/Field Injection of Dependencies so we can handle BeanCurrentlyInCreationException
    @Autowired
    public void setMovieRepository(MovieRepository movieRepository, MovieSpecification movieSpecification, MovieMapper movieMapper) {
        this.movieRepository = movieRepository;
        this.movieSpecification = movieSpecification;
        this.movieMapper = movieMapper;
    }

    /**
     * Saves the DTO received and returns it with its brand-new id
     * @param dto to be persisted
     * @return The same dto with its brand-new id
     */
    public MovieDTO save(MovieDTO dto) {

        //Validation of received DTO
        validation(dto);

        MovieEntity entity = movieMapper.movieDTO2Entity(dto);
        MovieEntity entitySaved = movieRepository.save(entity);
        MovieDTO result = movieMapper.movieEntity2DTO(entitySaved, true);

        return result;
    }

    /**
     * Gets a List of all Movies from Database as Basic DTO
     * @return a List of all Movies as BasicDTO
     */
    public List<MovieBasicDTO> getBasicDTOList() {

        List<MovieEntity> movies = movieRepository.findAll();
        List<MovieBasicDTO> result = movieMapper.movieEntityList2BasicDTOList(movies);
        return result;
    }

    /**
     * Gets a List of all Movies from Database as DTO
     * @return a List of all Movies as DTO
     */
    public List<MovieDTO> getDTOList() {

        List<MovieEntity> entities = movieRepository.findAll();
        List<MovieDTO> dtos = movieMapper.movieEntityList2DTOList(entities, true);
        return dtos;
    }

    /**
     * Performs a logic delete to the Entity related to the received id
     * @param id of the entity to be deleted
     */
    public void delete(Long id) {
        if (movieRepository.findById(id) == null)
            throw new NotFoundException("Movie requested was not found.");
        movieRepository.deleteById(id);
    }

    /**
     * Updates an Entity related to the received id with the new attributes from the received dto
     * @param id of the entity to be updated
     * @param dto with all the new attributes
     * @return The Entity as DTO with its updated attributes
     * @throws NotFoundException
     */
    public MovieDTO update(Long id, MovieDTO dto) throws NotFoundException {

        //Validation of new attributes
        validation(dto);

        Optional<MovieEntity> result = movieRepository.findById(id);
        if (result.isPresent()) {
            MovieEntity entity = movieMapper.updateMovieDTO2Entity(result.get(), dto);
            MovieEntity entityUpdated = movieRepository.save(entity);
            MovieDTO dtoUpdated = movieMapper.movieEntity2DTO(entityUpdated, true);
            return dtoUpdated;
        } else {
            throw new NotFoundException("Requested movie was not found.");
        }
    }

    /**
     * Returns a List of MoviesDTOs that met the received filters
     * @param title Filter by title
     * @param idGenre Filter by Genre
     * @param order Order by CreationDate (ASC / DESC)
     * @return A List of MoviesDTO according to the specifications
     */
    public List<MovieDTO> getByFilters(String title, Long idGenre, String order) {

        /* Creates a filtersDTO with the specifications, brings all the entities which meet these and
        * then turns the list of entitites into a list of DTOs */
        MovieFiltersDTO filtersDTO = new MovieFiltersDTO(title, idGenre, order);
        List<MovieEntity> entities = movieRepository.findAll(movieSpecification.getByFilters(filtersDTO));
        List<MovieDTO> dtos = movieMapper.movieEntityList2DTOList(entities, true);
        return dtos;
    }

    /**
     * Turns the received List of Entities into a List of DTOs
     * @param associatedMovies Movies to be turned into a List of DTOs
     * @param loadCharacters Boolean to determine if characters should be loaded
     * @return A List of MoviesDTO
     */
    public List<MovieDTO> movieEntityList2DTOList(List<MovieEntity> associatedMovies, boolean loadCharacters) {
        return movieMapper.movieEntityList2DTOList(associatedMovies, loadCharacters);
    }

    /**
     * Validates all attributes of the received dto and throws an exception if any does not meet the requirements
     * @param dto to be validated
     */
    private void validation(MovieDTO dto) {
        if (dto == null)
            throw new InvalidDTOException("Movie cannot be null.");
        if (dto.getTitle() == null || dto.getTitle().isEmpty())
            throw new InvalidDTOException("Movie title cannot be empty or null");
        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new InvalidDTOException("Movie image cannot be empty or null");
        if (dto.getCreationDate() == null || dto.getCreationDate().isEmpty())
            throw new InvalidDTOException("Movie creation date cannot be empty or null");
        if (dto.getGenreId() == null)
            throw new InvalidDTOException("Movie genre id cannot be null");
        if (dto.getRating() == null)
            throw new InvalidDTOException("Movie rating cannot be null");
        else if (dto.getRating() < 1 || dto.getRating() > 5)
            throw new InvalidDTOException("Movie rating cannot be less than 1 or greater than 5.");
        if (dto.getCharacters() == null || dto.getCharacters().isEmpty())
            throw new InvalidDTOException("Movie should have at least 1 character");
    }
}
