package com.cjj.myapplication.service.serviceImpl;

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
    /**
     * 调用分页插件完成分页
     */
    private PageInfo<Film> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        List<Film> filmList = filmMapper.selectPage();
        return new PageInfo<Film>(filmList);
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
}
