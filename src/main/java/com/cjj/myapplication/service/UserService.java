package com.cjj.myapplication.service;

import com.cjj.myapplication.model.User;

import java.util.List;

public interface UserService {
         List<User> selectAll();
         User selectUserByNameAndPassword(User user);
         User getUserInfoById(int userid);
}
