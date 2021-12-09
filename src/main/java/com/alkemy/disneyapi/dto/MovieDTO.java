package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class MovieDTO {

    //ATRIBUTES
    private Long id;
    private String image;
    private String title;
    private String creationDate;
    private Byte rating;
    private Long genreId;
    private List<CharacterDTO> characters;

}
