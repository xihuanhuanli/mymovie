package com.cjj.myapplication.service.serviceImpl;

import com.cjj.myapplication.mapper.FilmSceneMapper;
import com.cjj.myapplication.model.FilmScene;
import com.cjj.myapplication.service.FilmSceneService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

@Service
public class FilmSceneServiceImpl implements FilmSceneService {
    @Resource
    private FilmSceneMapper filmSceneMapper;
    @Override
    public void addFilmScene(FilmScene film) {
            filmSceneMapper.addFilmScene(film);
    }

    @Override
    public Map selectFilmScene(FilmScene film) {
        Map filmScene=filmSceneMapper.selectFilmScene(film);
        return filmScene;
    }

    @Override
    public Map<String, Object> getFilmInfoByFSID(Integer filmSceneId) {
        Map<String,Object> map=filmSceneMapper.getFilmInfoByFSID(filmSceneId);
        return map;
    }
}
