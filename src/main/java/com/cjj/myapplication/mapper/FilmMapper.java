package com.cjj.myapplication.mapper;


import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.model.Film;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.List;
import java.util.Map;

public interface FilmMapper {
    List<Film> selectAll();

    List<FilmDTO> selectPage(@Param("search") String search);

    void addFilm(@Param("film") Film film);

    void deleteFilm(@Param("id") int id);

    void updateFilm(@Param("film") Film film);

    Film selectFilmByfilmName(@Param("filmname")String filmname);

    @MapKey("id")
    List<Map> selectShowFilm();
    @MapKey("id")
    List<Map> selectShowFilmPage(@Param("search")String search);

    void deleteShowFilm(@Param("id")int id);

    void updateShowFilm(@Param("id")int id,@Param("showFilmId") int showFilmId);

    void addShowFilm(@Param("showFilmId")int showFilmId);
    @MapKey("id")
    Map selectShowFilmByID(@Param("id")int id);

    Film selectFilmByfilmID(@Param("id")int id);
}
