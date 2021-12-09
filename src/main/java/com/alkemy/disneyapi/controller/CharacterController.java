package com.alkemy.disneyapi.controller;

import com.alkemy.disneyapi.dto.CharacterBasicDTO;
import com.alkemy.disneyapi.dto.CharacterDTO;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.service.CharacterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("characters")
public class CharacterController {

    @Autowired
    private CharacterService characterService;

    /**
     * Returns a list of characters with only their image and name attributes
     *
     * @return a list of characters with only their image and name attributes
     */
    @GetMapping("/all-basic")
    public ResponseEntity<List<CharacterBasicDTO>> getBasicCharacterDTO() {

        List<CharacterBasicDTO> charactersBasic = characterService.getBasicDTOList();
        return ResponseEntity.ok().body(charactersBasic);
    }

    @GetMapping
    public ResponseEntity<List<CharacterDTO>> getDetailsByFilters(
            @RequestParam(required = false) String name,
            @RequestParam(required = false) Byte age,
            @RequestParam(required = false) Set<Long> idMovies
            ) {
        List<CharacterDTO> characters = characterService.getByFilters(name, age, idMovies);
        return ResponseEntity.ok().body(characters);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CharacterDTO>> getAll() {

        List<CharacterDTO> characters = characterService.getDTOList();
        return ResponseEntity.ok().body(characters);

    }

    /**
     * Insert a new character to the DB and returns it with its id
     *
     * @param character CharacterDTO attributes
     * @return The Character created with its id
     */
    @PostMapping
    public ResponseEntity<CharacterDTO> save(@RequestBody CharacterDTO character) {
        CharacterDTO characterUpdated = characterService.save(character);
        return ResponseEntity.status(HttpStatus.CREATED).body(characterUpdated);
    }

    /**
     * Performs a logic delete to the Entity related to the id received
     *
     * @param id Of the entity to delete
     * @return 204 Code (Success)
     */
    @DeleteMapping("{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        characterService.delete(id);
        return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<CharacterDTO> update(@PathVariable Long id, @RequestBody CharacterDTO dto) throws NotFoundException {
        CharacterDTO result = characterService.update(id, dto);
        return ResponseEntity.ok().body(result);
    }

}
