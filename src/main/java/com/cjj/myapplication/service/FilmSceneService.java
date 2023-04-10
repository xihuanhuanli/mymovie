package com.cjj.myapplication.service;

import com.cjj.myapplication.api.dto.GetOrederModel;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.model.FilmScene;

import java.util.Map;

public interface FilmSceneService {
    void addFilmScene(FilmScene film);

    Map selectFilmScene(FilmScene film);

    Map<String, Object> getFilmInfoByFSID(Integer filmSceneId);

    boolean setOrder(GetOrederModel getOrederModel);

    PageResult findOrderPage(PageRequest pageQuery);
}
