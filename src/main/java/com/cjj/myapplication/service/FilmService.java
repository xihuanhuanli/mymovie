package com.cjj.myapplication.service;



import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.model.Film;

import java.util.List;
import java.util.Map;

public interface FilmService {
    List<Film> selectAll();
    /**
     * 分页查询接口
     * 这里统一封装了分页请求和结果，避免直接引入具体框架的分页对象, 如MyBatis或JPA的分页对象
     * 从而避免因为替换ORM框架而导致服务层、控制层的分页接口也需要变动的情况，替换ORM框架也不会
     * 影响服务层以上的分页接口，起到了解耦的作用
     * @param pageRequest 自定义，统一分页查询请求
     * @return PageResult 自定义，统一分页查询结果
     */
    PageResult findPage(PageRequest pageRequest);


    void addFilm(Film film);

    void deleteFilm(int id);

    void updateFilm(Film film);


    Film selectFilmByfilmName(String filmname);

    List<Map> selectShowFilm();

    PageResult findShowFilmPage(PageRequest pageQuery);

    void deleteShowFilm(int id);

    void updateShowFilm(int id, int showFilmId);

    void addShowFilm(int showFilmId);

    Map selectShowFilmByID(int id);

    Film selectFilmByfilmID(int id);
}
