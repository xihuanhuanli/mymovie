package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.UserAPI;
import com.cjj.myapplication.model.User;
import com.cjj.myapplication.service.UserService;
import com.cjj.myapplication.service.serviceImpl.UserServiceImpl;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class UserController implements UserAPI {
    @Resource
    private UserService userService =new UserServiceImpl();
    @Override
    public List<User> selectAll() {
        List<User> list=userService.selectAll();
        return list;
    }
}
