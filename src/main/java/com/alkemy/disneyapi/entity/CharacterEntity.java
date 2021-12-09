package com.alkemy.disneyapi.entity;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "characters")
@Getter
@Setter
@SQLDelete(sql = "UPDATE characters SET deleted = true WHERE id=?")
@Where(clause = "deleted=false")
public class CharacterEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    // ATRIBUTOS
    private String image;
    private String name;
    private Byte age;
    private Float weight;
    private String story;
    private boolean deleted = Boolean.FALSE;

    // RELACIÓN
    @Column(name = "associated_movies")
    @ManyToMany(mappedBy = "characters", cascade = CascadeType.ALL)
    private List<MovieEntity> associatedMovies = new ArrayList();

}
