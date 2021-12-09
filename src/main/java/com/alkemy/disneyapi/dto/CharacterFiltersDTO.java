package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Set;

@Getter
@Setter
public class CharacterFiltersDTO {

    //ATRIBUTOS
    private String name;
    private Byte age;
    private Set<Long> idMovies;

    public CharacterFiltersDTO(String name, Byte age, Set<Long> idMovies) {
        this.name = name;
        this.age = age;
        this.idMovies = idMovies;
    }
}
