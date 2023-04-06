package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.FilmAPI;
import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.api.dto.ShowFilmDTO;
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
import java.util.Map;

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
    public ResponseData<PageResult> selectShowFilmList(PageRequest pageQuery) {
        PageResult pageResult =filmService.findShowFilmPage(pageQuery);
        ResponseData responseData=new ResponseData<>(0,"success",pageResult);
        return responseData;
    }

    @Override
    public ResponseData deleteShowFilm(ShowFilmDTO showFilmDTO) {
        filmService.deleteShowFilm(showFilmDTO.getId());
        ResponseData responseData=new ResponseData<>(0,"success");
        return responseData;
    }

    @Override
    public ResponseData updateShowFilm(ShowFilmDTO showFilmDTO) {
        if(selectFilmByfilmID(showFilmDTO.getShow_film_id())==null){
            ResponseData responseData=new ResponseData<>(2,"filmNOTExist!");
            return responseData;
        }
        filmService.updateShowFilm(showFilmDTO.getId(),showFilmDTO.getShow_film_id());
        ResponseData responseData=new ResponseData(0,"success");
        return responseData;
    }

    @Override
    public ResponseData addShowFilm(ShowFilmDTO showFilmDTO) {
        if(selectFilmByfilmID(showFilmDTO.getShow_film_id())==null){
            ResponseData responseData=new ResponseData<>(2,"filmNOTExist!");
            return responseData;
        }
        Map map=selectShowFilmByID(showFilmDTO.getShow_film_id());
        if(map!=null&&map.size()!=0){
            ResponseData responseData=new ResponseData<>(1,"showfilmExist!");
            return responseData;
        }
        filmService.addShowFilm(showFilmDTO.getShow_film_id());
        ResponseData responseData=new ResponseData<>(0,"success");
        return responseData;
    }

    @Override
    public ResponseData selectfilmByfilmID(FilmDTO filmdto) {
        Film film1=filmService.selectFilmByfilmID(filmdto.getId());
        if(film1==null){
            ResponseData responseData=new ResponseData<>(1,"filmNOTExist!");
            return responseData;
        }
        String s=film1.getImage_src();
        s="https://images.weserv.nl/?url="+s;
        film1.setImage_src(s);
        FilmDTO filmDTO=filmConverter.FilmToFilmDTO(film1);
        ResponseData responseData=new ResponseData<>(0,"success",filmDTO);
        return responseData;
    }


    @Override
    public ResponseData addFilm(FilmDTO filmDTO) {
        Film film=filmConverter.FilmDTOToFilm(filmDTO);
        if(film.getFilm_name()!=null&&film.getFilm_name()!=""){
            Film filmtest=selectFilmByfilmName(film.getFilm_name());
            if(filmtest!=null){
                ResponseData responseData=new ResponseData<>(1,"filmExist!");
                return responseData;
            }
        }
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

    @Override
    public ResponseData selectShowFilm() {
        List<Map> map=filmService.selectShowFilm();
        for(Map<String, Object> map1 : map){
            String s=(String) map1.get("image_src");
            s="https://images.weserv.nl/?url="+s;
            map1.put("image_src",s);
        }
        ResponseData responseData=new ResponseData(0,"success",map);
        return responseData;
    }



    public Film selectFilmByfilmName(String filmname){
        Film film1=filmService.selectFilmByfilmName(filmname);
        return film1;
    }
    public Film selectFilmByfilmID(int id){
        Film film1=filmService.selectFilmByfilmID(id);
        return film1;
    }
    public Map selectShowFilmByID(int id){
        Map map=filmService.selectShowFilmByID(id);
        return map;
    }
}
