package com.cjj.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Film {
    private Integer id;
    private String film_name;
    private String initial_name;
    private String country;
    private String release_year;
    private String type;
    private String score;
    private String evaluate_number;
    private String role;
    private String introduction;
    private String image_src;
    private String alias;
    private String language;
    private String time;
    private String details_src;
    private LocalDateTime createDate;
    private int createBy;
    private LocalDateTime updateDate;
    private int updateBy;
    private int isDeleted;
    private int version;
}
