package com.cjj.myapplication.service.serviceImpl;


import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.PageUtils.PageUtils;
import com.cjj.myapplication.mapper.UserMapper;
import com.cjj.myapplication.model.User;
import com.cjj.myapplication.service.UserService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service("UserService")
public class UserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;

    @Override
    public User selectUserByNameAndPassword(User user) {
        return userMapper.selectUserByNameAndPassword(user);
    }

    @Override
    public User getUserInfoById(int userid) {
        return userMapper.getUserInfoById(userid);
    }

    @Override
    public PageResult findUserPage(PageRequest pageRequest) {
        return PageUtils.getPageResult(pageRequest, getPageInfo(pageRequest));
    }

    @Override
    public void addUser(User user) {
        userMapper.addUser(user);
    }

    @Override
    public void deleteUser(int id) {
        userMapper.deleteUser(id);
    }

    @Override
    public void updateUser(User user) {
        userMapper.updateUser(user);
    }

    private PageInfo<User> getPageInfo(PageRequest pageRequest) {
        int pageNum = pageRequest.getPageNum();
        int pageSize = pageRequest.getPageSize();
        PageHelper.startPage(pageNum, pageSize);
        String  search=pageRequest.getSearch();
        List<User> filmList=userMapper.selectUserPage(search);
        return new PageInfo<User>(filmList);
    }
}
