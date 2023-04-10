package com.cjj.myapplication.api;

import com.cjj.myapplication.api.dto.GetOrederModel;
import com.cjj.myapplication.common.PageUtils.PageRequest;
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
    @RequestMapping(value = "/getFilmInfoByFSID",method = RequestMethod.POST)
    ResponseData getFilmInfoByFSID(@RequestBody FilmScene film);

    @RequestMapping(value = "/setOrder",method = RequestMethod.POST)
    ResponseData setOrder(@RequestBody GetOrederModel getOrederModel);

    @RequestMapping(value = "/selectOrder",method = RequestMethod.POST)
    ResponseData selectOrder(@RequestBody PageRequest pageQuery);
}
