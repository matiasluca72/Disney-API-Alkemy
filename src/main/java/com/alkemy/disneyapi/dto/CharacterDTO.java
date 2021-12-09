package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CharacterDTO {

    //ATRIBUTOS
    private Long id;
    private String image;
    private String name;
    private Byte age;
    private Float weight;
    private String story;

}
