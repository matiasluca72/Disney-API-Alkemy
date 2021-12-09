package com.alkemy.disneyapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;

@Entity // Anotación para JPA - La Entidad será una Tabla en nuestra DB
@Table(name = "genre") // Cambiamos el nombre de la tabla en la DB
@Getter // Generamos getters con lombok
@Setter // Generamos setters con lombok
@SQLDelete(sql = "UPDATE genre SET deleted = true WHERE id=?") // Indicamos que query ejecutar al llamar un DELETE
@Where(clause = "deleted=false") // Le agrega una clausula extra a las queries que se realicen para diferenciar a los borrados de los no borrados
public class GenreEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE) // 1, 2, 3...
    private Long id;

    //Atributos
    private String name;
    private String image;
    private boolean deleted = Boolean.FALSE; // Booleano para ejecutar un SOFT DELETE

    //Relación con la Clase Pelicula - Quedará de forma implícita
}
