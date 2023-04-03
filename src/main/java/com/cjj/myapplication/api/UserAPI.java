package com.cjj.myapplication.api;

import com.cjj.myapplication.api.dto.LoginDTO;
import com.cjj.myapplication.api.dto.UserDTO;
import com.cjj.myapplication.api.dto.WeiXinCheckUrl;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.ResponseData;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;


import java.util.Map;

@RequestMapping(value = "/user")
public interface UserAPI {

    @RequestMapping(value = "/selectALLPageUser",method = RequestMethod.POST)
    ResponseData<PageResult> selectAllPageUser(@RequestBody PageRequest pageQuery);

    @RequestMapping(value = "/addUser",method = RequestMethod.POST)
    ResponseData addUser(@RequestBody UserDTO userDTO);

    @RequestMapping(value = "/deleteUser",method = RequestMethod.POST)
    ResponseData deleteUser(@RequestBody UserDTO userDTO);

    @RequestMapping(value = "/updateUser",method = RequestMethod.POST)
    ResponseData updateUser(@RequestBody UserDTO userDTO);

    @RequestMapping(value="/login", method = RequestMethod.POST)
    ResponseData<LoginDTO> login(@RequestBody UserDTO userDTO);

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    ResponseData<UserDTO> getUserInfo(@RequestBody Map<String,String> map);

    @RequestMapping(value = "/logout",method = RequestMethod.POST)
    ResponseData<UserDTO> logout();
    @RequestMapping(value = "/checkUrl",method = RequestMethod.GET)
     String checkUrl( WeiXinCheckUrl weiXinCheckUrl);
    @RequestMapping(value = "/getUrl",method = RequestMethod.GET)
    ResponseData getUrl();



}
