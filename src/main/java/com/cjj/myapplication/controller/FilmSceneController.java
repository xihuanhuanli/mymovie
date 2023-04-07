package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.FilmSceneAPI;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.model.FilmScene;
import com.cjj.myapplication.service.FilmSceneService;
import com.cjj.myapplication.service.serviceImpl.FilmSceneServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Map;


@RestController
@Slf4j
public class FilmSceneController implements FilmSceneAPI {
    @Resource
    private FilmSceneService filmSceneService=new FilmSceneServiceImpl();
    @Override
    public ResponseData addFilmScene(FilmScene film) {
            filmSceneService.addFilmScene(film);
            ResponseData responseData=new ResponseData<>(0,"success");
            return responseData;
    }

    @Override
    public ResponseData selectFilmScene(FilmScene film) {
        Map<String,Object> mapnew= filmSceneService.selectFilmScene(film);
        Map map= (Map) mapnew.get(film.getFilm_id());
        if(map!=null) {
            ResponseData responseData = new ResponseData<>(0, "success", map);
            return responseData;
        }else {
            addFilmScene(film);
            Map<String,Object> mapnew1 = filmSceneService.selectFilmScene(film);
            Map map1= (Map) mapnew1.get(film.getFilm_id());
            ResponseData responseData = new ResponseData<>(0, "success", map1);
            return responseData;
        }
    }
}
