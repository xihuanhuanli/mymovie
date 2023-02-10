package com.cjj.myapplication.mapper;

import com.cjj.myapplication.model.User;


import java.util.List;



public interface UserMapper {


    List<User> selectAll();

    User selectUserByNameAndPassword(User user);


}
