package com.cjj.myapplication.service;

import com.cjj.myapplication.model.FilmScene;

import java.util.Map;

public interface FilmSceneService {
    void addFilmScene(FilmScene film);

    Map selectFilmScene(FilmScene film);
}
