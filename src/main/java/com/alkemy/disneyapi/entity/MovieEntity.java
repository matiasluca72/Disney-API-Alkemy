package com.alkemy.disneyapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;
import org.springframework.format.annotation.DateTimeFormat;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "movies")
@Getter
@Setter
@SQLDelete(sql = "UPDATE movies SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // ATRIBUTOS
    private String image;
    private String title;
    @Column(name = "creation_date") // Modificamos el nombre de la columna a algo más coherente para la DB
    @DateTimeFormat(pattern = "yyyy/MM/dd") // Darle formato a la fecha
    private LocalDate creationDate;
    private Byte rating; // Del 1 al 5
    private boolean deleted = Boolean.FALSE;

    // RELACIÓNES
    // El Objeto GeneroEntity, este atributo será solo para traer la información del Genero
    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "genre_id", insertable = false, updatable = false)
    private GenreEntity genre;

    // Id del Genero setteado, este atributo será para settearlo o modificarlo de la PeliculaEntity
    @Column(name = "genre_id", nullable = false)
    private Long genreId;

    //El tipo de cascada de la relación será con las operaciones de persistir y mergear
    @ManyToMany(
            cascade = {
                    CascadeType.PERSIST,
                    CascadeType.MERGE
            })
    // Definimos el nombre de la tabla intermedia y los nombres de ambas columnas
    @JoinTable(
            name = "movies_characters",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "character_id"))
    private List<CharacterEntity> characters = new ArrayList();
}
