package com.alkemy.disneyapi.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class MovieFiltersDTO {

    //ATRIBUTOS
    private String title;
    private Long idGenre;
    private String order;

    //CONSTRUCTOR
    public MovieFiltersDTO(String title, Long idGenre, String order) {
        this.title = title;
        this.idGenre = idGenre;
        this.order = order;
    }

    //Order of the results
    public boolean isASC() { return order.compareToIgnoreCase("ASC") == 0; }
    public boolean isDESC() { return order.compareToIgnoreCase("DESC") == 0; }

}
