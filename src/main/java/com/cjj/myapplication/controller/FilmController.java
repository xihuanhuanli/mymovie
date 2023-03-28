package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.FilmAPI;
import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.PageUtils.PageUtils;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.converter.FilmConverter;
import com.cjj.myapplication.model.Film;
import com.cjj.myapplication.service.FilmService;
import com.cjj.myapplication.service.serviceImpl.FilmServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class FilmController implements FilmAPI {
    @Resource
    private FilmService filmService =new FilmServiceImpl();

    FilmConverter filmConverter=FilmConverter.INSTANCE;

    @Override
    public ResponseData<List<FilmDTO>> selectAll() {
        List<Film> list =filmService.selectAll();
        List<FilmDTO> dtoList=filmConverter.FilmListToFilmDTOList(list);
        ResponseData responseData=new ResponseData<>(0,"success",dtoList);
        return responseData;
    }

    @Override
    public ResponseData<PageResult> selectAllPage(PageRequest pageQuery) {
        PageResult pageResult =filmService.findPage(pageQuery);
        ResponseData responseData=new ResponseData<>(0,"success",pageResult);
        return responseData;
    }


    @Override
    public ResponseData addFilm(FilmDTO filmDTO) {
        Film film=filmConverter.FilmDTOToFilm(filmDTO);
        filmService.addFilm(film);
        ResponseData responseData=new ResponseData<>(0,"success");
        return responseData;
    }

    @Override
    public ResponseData deleteFilm(FilmDTO filmDTO) {
        Film film=filmConverter.FilmDTOToFilm(filmDTO);
        filmService.deleteFilm(film.getId());
        ResponseData responseData=new ResponseData<>(0,"success");
        return responseData;
    }

    @Override
    public ResponseData updateFilm(FilmDTO filmDTO) {
        Film film=filmConverter.FilmDTOToFilm(filmDTO);
        filmService.updateFilm(film);
        ResponseData responseData=new ResponseData(0,"success");
        return responseData;
    }
}
