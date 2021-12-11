package com.alkemy.disneyapi.controller;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController // Marcamos a la Clase como un componente RestControlador
@RequestMapping("genres") // Definimos el path para este controlador
public class GenreController {

    // Inyectamos una instancia de nuestro GenreService
    @Autowired
    private GenreService genreService;

    //Añadimos un EndPoint que responderá al verbo GET y devolverá un Listado con todos los DTO en la DB
    @GetMapping
    public ResponseEntity<List<GenreDTO>> getAll() {

        // Invocamos un método del Service y lo guardamos en una Lista de DTO
        List<GenreDTO> genres = genreService.getAllGenres();

        //Devolvemos la Entidad de Respuesta con un ok y en el cuerpo la lista de DTOs
        return ResponseEntity.ok().body(genres);
    }

    //Añadimos un EndPoint que devolverá un ResponseEntity<T> y pide un parámetro desde el Body de tipo GenreDTO
    @PostMapping // Definimos el verbo o método del controlador
    public ResponseEntity<GenreDTO> save(@RequestBody GenreDTO genre) {

        // Guardamos el género usando el Service de la entidad
        GenreDTO genreUpdated = genreService.save(genre);

        // Respondemos con un código HTTP 201 y devolvemos el género guardado con su ID
        return ResponseEntity.status(HttpStatus.CREATED).body(genreUpdated);
    }

    //EndPoint para eliminar el registro con el id pasado como Path Variable
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {

        //Le pasamos el parámetro recibido desde el path al método de eliminación del Service
        genreService.delete(id);

        //Devolvemos el código 201 de creado
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    //EndPoint para actualizar los atributos del registro correspondiente al id pasado como Path Variable
    @PutMapping("/{id}")
    public ResponseEntity<GenreDTO> update(@PathVariable Long id, @RequestBody GenreDTO dto) throws NotFoundException {

        //Llamamos al método del Service pasandole el id del registro a actualizar y el dto con todos los atributos nuevos
        GenreDTO result = genreService.update(id, dto);

        //Devolvemos un ResponseEntity con el dto actualizado en el body
        return ResponseEntity.ok().body(result);
    }
}
