package com.cjj.myapplication.api;

import com.cjj.myapplication.api.dto.FilmDTO;
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
    @RequestMapping(value = "/selectALL",method = RequestMethod.POST)
    ResponseData<List<FilmDTO>> selectAll();

    @RequestMapping(value = "/selectALLPage",method = RequestMethod.POST)
    ResponseData<PageResult> selectAllPage(@RequestBody PageRequest pageQuery);

    @RequestMapping(value = "/addFilm",method = RequestMethod.POST)
    ResponseData addFilm(@RequestBody FilmDTO filmDTO);

    @RequestMapping(value = "/deleteFilm",method = RequestMethod.POST)
    ResponseData deleteFilm(@RequestBody FilmDTO filmDTO);

    @RequestMapping(value = "/updateFilm",method = RequestMethod.POST)
    ResponseData updateFilm(@RequestBody FilmDTO filmDTO);

    @RequestMapping(value = "/selectALLwithPage",method = RequestMethod.POST)
         ResponseData<?> selectALLwithPage(@RequestParam(defaultValue = "1") Integer pageNum,
                                    @RequestParam(defaultValue = "10") Integer pageSize,
                                    @RequestParam(defaultValue = "") String search);


}
