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

    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<MovieBasicDTO>> getBasicAll() {
        List<MovieBasicDTO> moviesBasic = movieService.getBasicDTOList();
        return ResponseEntity.ok().body(moviesBasic);
    }

    @GetMapping("/all")
    public ResponseEntity<List<MovieDTO>> getAll() {
        List<MovieDTO> movies = movieService.getDTOList();
        return ResponseEntity.ok().body(movies);
    }

    @GetMapping("/filters")
    public ResponseEntity<List<MovieDTO>> getDetailsByFilters(
            @RequestParam(required = false) String title,
            @RequestParam(required = false) Long idGenre,
            @RequestParam(required = false, defaultValue = "ASC") String order
    ) {
        List<MovieDTO> movies = movieService.getByFilters(title, idGenre, order);
        return ResponseEntity.ok().body(movies);
    }

    @PostMapping
    public ResponseEntity<MovieDTO> save(@RequestBody MovieDTO dto) {
        MovieDTO dtoUpdated = movieService.save(dto);
        return ResponseEntity.status(HttpStatus.CREATED).body(dtoUpdated);
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        movieService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<MovieDTO> update(@PathVariable Long id, @RequestBody MovieDTO dto) throws NotFoundException {
        MovieDTO result = movieService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }
}
