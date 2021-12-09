package com.alkemy.disneyapi.service.implementation;

import com.alkemy.disneyapi.dto.GenreDTO;
import com.alkemy.disneyapi.entity.GenreEntity;
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

    //Creamos un método para guardar el género recibido en formato DTO y devolverlo persistido
    public GenreDTO save(GenreDTO dto) {

        //Utilizamos el método de conversión del Mapper para pasarle el DTO recibido y obtener el Entity
        GenreEntity entity = genreMapper.genreDTO2Entity(dto);

        //Persistimos el DTO ahora hecho Entidad en la DB
        GenreEntity entitySaved = genreRepository.save(entity);

        //Convertimos la Entidad ya persistida de vuelta en un DTO, ahora con id
        GenreDTO result = genreMapper.genreEntity2DTO(entitySaved);

        //Devolvemos el DTO con su id en la base de datos
        return result;

    }

    //Método para devolver un Listado con todos los Géneros en formato DTO
    public List<GenreDTO> getAllGenres() {

        //Traemos todos los géneros como Entidades del Repository
        List<GenreEntity> genres = genreRepository.findAll();

        //Convertimos esa lista en tipo DTO usando un método de nuestro Mapper
        List<GenreDTO> result = genreMapper.genreEntityList2DTOList(genres);

        //Devolvemos la Lista con los Géneros en formato DTO
        return result;
    }

    //Método para eliminar un registro en la DB correspondiente al id
    public void delete(Long id) {
        genreRepository.deleteById(id);
    }


    public GenreDTO update(Long id, GenreDTO dto) throws NotFoundException {

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

}
