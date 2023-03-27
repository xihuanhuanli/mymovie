package com.cjj.myapplication.mapper;


import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.model.Film;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface FilmMapper {
    List<Film> selectAll();

    List<Film> selectPage();

    void addFilm(@Param("film") Film film);

    void deleteFilm(@Param("id") int id);

    void updateFilm(@Param("film") Film film);
}
