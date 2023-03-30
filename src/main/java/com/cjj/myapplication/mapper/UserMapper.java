package com.cjj.myapplication.mapper;

import com.cjj.myapplication.model.User;
import org.apache.ibatis.annotations.Param;


import java.util.List;



public interface UserMapper {



    User selectUserByNameAndPassword(User user);

    User getUserInfoById(int userid);


    List<User> selectUserPage(@Param("search") String search);

    void addUser(@Param("user") User user);

    void deleteUser(@Param("id") int id);

    void updateUser(@Param("user") User user);

    User selectUserByUserName(@Param("username")String username);
}
