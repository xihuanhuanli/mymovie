package com.cjj.myapplication.service.serviceImpl;

import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.PageUtils.PageUtils;
import com.cjj.myapplication.mapper.FilmMapper;
import com.cjj.myapplication.model.Film;
import com.cjj.myapplication.service.FilmService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Service;



import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class FilmServiceImpl implements FilmService {
    @Resource
    private FilmMapper filmMapper;
    @Override
    public List<Film> selectAll() {
        return filmMapper.selectAll();
    }

    @Override
    public PageResult findPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }
    @Override
    public PageResult findShowFilmPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getShowFilmPageInfo(pageRequest));
    }

    @Override
    public void deleteShowFilm(int id) {
        filmMapper.deleteShowFilm(id);
    }

    @Override
    public void updateShowFilm(int id, int showFilmId) {
        filmMapper.updateShowFilm(id,showFilmId);
    }

    @Override
    public void addShowFilm(int showFilmId) {
        filmMapper.addShowFilm(showFilmId);
    }

    @Override
    public Map selectShowFilmByID(int id) {
        Map map=filmMapper.selectShowFilmByID(id);
        return map;
    }

    @Override
    public Film selectFilmByfilmID(int id) {
        Film film=filmMapper.selectFilmByfilmID(id);
        return film;
    }

    private PageInfo<?> getShowFilmPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        String  search=pageRequest.getSearch();
        List<Map> list=filmMapper.selectShowFilmPage(search);
        return new PageInfo<>(list);
    }

    /**
     * 调用分页插件完成分页
     */
    private PageInfo<FilmDTO> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        String  search=pageRequest.getSearch();
        List<FilmDTO> filmList=filmMapper.selectPage(search);
        for(FilmDTO film : filmList){
            String s=film.getImageSrc();
            s="https://images.weserv.nl/?url="+s;
            film.setImageSrc(s);
        }
        return new PageInfo<FilmDTO>(filmList);
    }


    @Override
    public void addFilm(Film film) {
        filmMapper.addFilm(film);
    }

    @Override
    public void deleteFilm(int id) {
        filmMapper.deleteFilm(id);
    }

    @Override
    public void updateFilm(Film film) {
        filmMapper.updateFilm(film);
    }

    @Override
    public Film selectFilmByfilmName(String filmname) {
        Film film=filmMapper.selectFilmByfilmName(filmname);
        return film;
    }

    @Override
    public List<Map> selectShowFilm() {
        List<Map> map=filmMapper.selectShowFilm();
        return map;
    }


}
