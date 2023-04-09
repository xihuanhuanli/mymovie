package com.cjj.myapplication.mapper;

import com.cjj.myapplication.model.FilmScene;
import org.apache.ibatis.annotations.MapKey;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

public interface FilmSceneMapper {
    void addFilmScene(@Param("filmScene")FilmScene filmScene);

    @MapKey("film_id")
    Map selectFilmScene(@Param("filmScene")FilmScene film);

    @MapKey("film_scene_id")
    Map<String, Object> getFilmInfoByFSID(@Param("filmSceneId")Integer filmSceneId);
}
