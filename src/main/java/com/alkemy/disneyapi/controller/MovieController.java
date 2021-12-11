package com.alkemy.disneyapi.controller;

import com.alkemy.disneyapi.dto.MovieBasicDTO;
import com.alkemy.disneyapi.dto.MovieDTO;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.service.MovieService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("movies")
public class MovieController {

    // Service
    @Autowired
    private MovieService movieService;

    /**
     * Gets a List of MovieBasicDTO from DB
     * @return a List of MovieBasicDTO from DB
     */
    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> moviesBasic = movieService.getBasicDTOList();
        return ResponseEntity.ok().body(moviesBasic);
    }

    /**
     * Gets a List of all the Movies from DB as DTOs
     * @return a List of all the Movies from DB as DTOs
     */
    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getDTOList();
        return ResponseEntity.ok().body(movies);
    }

    /**
     * Returns a List of Movies as DTO that meet the specifications received
     * @param title Filter by title
     * @param idGenre Filter by genre
     * @param order Order by CreationDate (only accepting ASC / DESC)
     * @return a List of Movies as DTO that meet the specifications received
     */
    @GetMapping("/filters")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> movies = movieService.getByFilters(title, idGenre, order);
        return ResponseEntity.ok().body(movies);
    }

    /**
     * Persists the received MovieDTO in the Database as an Entity. Then, returns it back as a DTO with its brand-new id
     * @param dto To be persisted
     * @return the same dto with its brand-new id
     */
    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto) {
        MovieDTO dtoUpdated = movieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoUpdated);
    }

    /**
     * Performs a logic delete to the Entity in the Database related to the received id
     * @param id Of the entity to be deleted
     * @return A 204 HttpCode with no content
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    /**
     * Updates the Entity in the database related to the received id with the attributes of the received MovieDTO.
     * Then, returns the Entity with its attributes updated
     * @param id Of the Entity to be updated
     * @param dto With all the updated attributes to be set (must be complete)
     * @return The Entity already updated
     * @throws NotFoundException
     */
    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) throws NotFoundException {
        MovieDTO result = movieService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
}
