package com.cjj.myapplication.api;

import com.cjj.myapplication.model.User;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/user")
public interface UserAPI {

    @RequestMapping(value = "/selectALL",method = RequestMethod.GET)
    List<User> selectAll();

}
