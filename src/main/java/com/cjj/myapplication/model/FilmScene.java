package com.cjj.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class FilmScene {
    private Integer film_scene_id;
    private Integer film_id;
    private String cinema;
    private LocalDate date;
    private LocalDateTime begin_time;
    private String cinema_type;
    private Integer seat_number;

}
