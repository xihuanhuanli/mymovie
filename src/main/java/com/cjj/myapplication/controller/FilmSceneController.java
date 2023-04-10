package com.cjj.myapplication.controller;

import com.alibaba.fastjson.JSONObject;
import com.cjj.myapplication.api.FilmSceneAPI;
import com.cjj.myapplication.api.dto.GetOrederModel;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.model.FilmScene;
import com.cjj.myapplication.service.FilmSceneService;
import com.cjj.myapplication.service.serviceImpl.FilmSceneServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
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

    @Override
    public ResponseData getFilmInfoByFSID(FilmScene film) {
        Map<String,Object> mapnew= filmSceneService.getFilmInfoByFSID(film.getFilm_scene_id());
        Map map= (Map) mapnew.get(film.getFilm_scene_id());
        String x=map.get("image_src").toString();
        x="https://images.weserv.nl/?url="+x;
        map.put("image_src",x);
        if(map.get("seat")!=null){
            String s= map.get("seat").toString();
            String[] strings=s.split(",");
            List<Map<String, Object>> statusFlowList = new ArrayList<>();
            for (String s1:strings) {
                JSONObject json1 = new JSONObject();
                json1.put("x",s1.split("/")[0]);
                json1.put("y",s1.split("/")[1]);
                statusFlowList.add(json1);
            }
            map.put("seat",statusFlowList);
        }

        ResponseData responseData = new ResponseData<>(0, "success",map);
        return responseData;
    }

    @Override
    public ResponseData setOrder(GetOrederModel getOrederModel) {
        if(filmSceneService.setOrder(getOrederModel)){
            ResponseData responseData = new ResponseData<>(0, "success");
            return responseData;
        }else{
        ResponseData responseData = new ResponseData<>(1, "write order not success");
        return responseData;
        }
    }
}
