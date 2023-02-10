package com.cjj.myapplication.api;

import com.cjj.myapplication.api.dto.LoginDTO;
import com.cjj.myapplication.api.dto.UserDTO;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.model.User;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@RequestMapping(value = "/user")
public interface UserAPI {

    @RequestMapping(value = "/selectALL",method = RequestMethod.POST)
    List<User> selectAll();

    @RequestMapping(value="/login", method = RequestMethod.POST)
    ResponseData<LoginDTO> login(@RequestBody UserDTO userDTO);

}
