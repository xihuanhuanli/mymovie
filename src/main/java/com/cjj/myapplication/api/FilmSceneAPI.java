package com.cjj.myapplication.api;

import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.model.FilmScene;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@RequestMapping(value = "/filmscene")
public interface FilmSceneAPI {
    @RequestMapping(value = "/addFilmScene",method = RequestMethod.POST)
    ResponseData addFilmScene(@RequestBody FilmScene film);
    @RequestMapping(value ="/selectFilmScene",method = RequestMethod.POST)
    ResponseData selectFilmScene(@RequestBody FilmScene film);
}
