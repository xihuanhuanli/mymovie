package com.cjj.myapplication.service.serviceImpl;


import com.cjj.myapplication.mapper.UserMapper;
import com.cjj.myapplication.model.User;
import com.cjj.myapplication.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    public List<User> selectAll() {
        return userMapper.selectAll();
    }

    @Override
    public User selectUserByNameAndPassword(User user) {
        return userMapper.selectUserByNameAndPassword(user);
    }
}
