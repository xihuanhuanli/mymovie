package com.cjj.myapplication.api;

import com.cjj.myapplication.api.dto.FilmDTO;
import com.cjj.myapplication.api.dto.ShowFilmDTO;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.PageUtils.PageUtils;
import com.cjj.myapplication.common.ResponseData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@RequestMapping(value = "/film")
public interface FilmAPI {
    /**
     * 查询列表（已停用） 改用分页查询
     */
    @RequestMapping(value = "/selectALL",method = RequestMethod.POST)
    ResponseData<List<FilmDTO>> selectAll();

    /**
     * 分页查询列表
     */
    @RequestMapping(value = "/selectALLPage",method = RequestMethod.POST)
    ResponseData<PageResult> selectAllPage(@RequestBody PageRequest pageQuery);

    /**
     * 新增电影
     * @param filmDTO
     * @return
     */
    @RequestMapping(value = "/addFilm",method = RequestMethod.POST)
    ResponseData addFilm(@RequestBody FilmDTO filmDTO);

    /**
     * 逻辑删除电影
     * @param filmDTO
     * @return
     */
    @RequestMapping(value = "/deleteFilm",method = RequestMethod.POST)
    ResponseData deleteFilm(@RequestBody FilmDTO filmDTO);

    /**
     * 更新电影信息by  id
     * @param filmDTO
     * @return
     */
    @RequestMapping(value = "/updateFilm",method = RequestMethod.POST)
    ResponseData updateFilm(@RequestBody FilmDTO filmDTO);

    @RequestMapping(value = "/selectShowFilm",method = RequestMethod.POST)
    ResponseData selectShowFilm();

    @RequestMapping(value = "/selectShowFilmList",method = RequestMethod.POST)
    ResponseData<PageResult> selectShowFilmList(@RequestBody PageRequest pageQuery);

    @RequestMapping(value = "/deleteShowFilm",method = RequestMethod.POST)
    ResponseData deleteShowFilm(@RequestBody ShowFilmDTO showFilmDTO);
    @RequestMapping(value = "/updateShowFilm",method = RequestMethod.POST)
    ResponseData updateShowFilm(@RequestBody ShowFilmDTO showFilmDTO);

    @RequestMapping(value = "/addShowFilm",method = RequestMethod.POST)
    ResponseData addShowFilm(@RequestBody ShowFilmDTO showFilmDTO);

}
