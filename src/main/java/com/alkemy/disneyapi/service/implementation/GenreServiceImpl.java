package com.alkemy.disneyapi.service.implementation;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.entity.GenreEntity;
import com.alkemy.disneyapi.exceptions.InvalidDTOException;
import com.alkemy.disneyapi.exceptions.NotFoundException;
import com.alkemy.disneyapi.mapper.GenreMapper;
import com.alkemy.disneyapi.repository.GenreRepository;
import com.alkemy.disneyapi.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service // Marcamos la Clase como un componente Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    private GenreMapper genreMapper;
    @Autowired
    private GenreRepository genreRepository;

    /**
     * Saves the received DTO and returns it with a brand-new id
     * @param dto To be persisted
     * @return The same DTO with a brand-new id
     */
    public GenreDTO save(GenreDTO dto) {

        //Validamos primero que el DTO sea válido
        validation(dto);

        //Utilizamos el método de conversión del Mapper para pasarle el DTO recibido y obtener el Entity
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);

        //Persistimos el DTO ahora hecho Entidad en la DB
        GenreEntity entitySaved = genreRepository.save(entity);

        //Convertimos la Entidad ya persistida de vuelta en un DTO, ahora con id
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);

        //Devolvemos el DTO con su id en la base de datos
        return result;
    }

    /**
     * Returns a List with all the genres in Database in DTO format
     * @return a List with all the genres in Database in DTO format
     */
    public List<GenreDTO> getAllGenres() {

        //Traemos todos los géneros como Entidades del Repository
        List<GenreEntity> genres = genreRepository.findAll();

        //Convertimos esa lista en tipo DTO usando un método de nuestro Mapper
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(genres);

        //Devolvemos la Lista con los Géneros en formato DTO
        return result;
    }

    /**
     * Performs a logic delete to the Entity related to the received id
     * @param id of the entity to be deleted
     */
    public void delete(Long id) {
        if (genreRepository.findById(id) == null)
            throw new NotFoundException("Requested genre was not found");
        genreRepository.deleteById(id);
    }

    /**
     * Updates attributes of the Entity related to the received id with the new attributes from the received DTO
     * @param id of the Entity to be updated
     * @param dto containing the new attributes
     * @return The Entity turned into DTO with its new attributes setted
     * @throws NotFoundException
     */
    public GenreDTO update(Long id, GenreDTO dto) throws NotFoundException {

        // Validación de los nuevos atributos
        validation(dto);

        /* Busca la entidad por ID y si lo encuentra, le settea los nuevos atributos, lo guarda en la BD
        * y lo devuelve en formato DTO ya actualizado */
        Optional<GenreEntity> result = genreRepository.findById(id);
        if (result.isPresent()) {
            GenreEntity entity = result.get();
            entity.setName(dto.getName());
            entity.setImage(dto.getImage());
            GenreEntity entityUpdated = genreRepository.save(entity);
            GenreDTO dtoUpdated = genreMapper.genreEntity2DTO(entityUpdated);
            return dtoUpdated;
        } else {
            throw new NotFoundException("Requested genre was not found.");
        }
    }

    /**
     * Validates the received dto and throws an Exception if any attribute does not meet the requirements
     * @param dto To be validated
     */
    private void validation(GenreDTO dto) {
        if (dto == null)
            throw new InvalidDTOException("Genre cannot be null");
        if (dto.getName() == null || dto.getName().isEmpty())
            throw new InvalidDTOException("Genre name cannot be empty or null");
        if (dto.getImage() == null || dto.getImage().isEmpty())
            throw new InvalidDTOException("Genre image cannot be empty or null");
    }
}
