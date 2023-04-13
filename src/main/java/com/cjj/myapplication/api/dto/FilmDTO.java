package com.cjj.myapplication.api.dto;

import lombok.Data;

import java.math.BigDecimal;

@Data
public class FilmDTO {
    private Integer id;
    private String filmName;
    private String initialName;
    private String country;
    private String releaseYear;
    private String type;
    private String score;
    private String evaluateNumber;
    private String role;
    private String introduction;
    private String imageSrc;
    private String alias;
    private String language;
    private String time;
    private BigDecimal price;
}
