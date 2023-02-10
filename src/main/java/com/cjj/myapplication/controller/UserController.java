package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.UserAPI;
import com.cjj.myapplication.api.dto.LoginDTO;
import com.cjj.myapplication.api.dto.UserDTO;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.converter.UserConverter;
import com.cjj.myapplication.model.User;
import com.cjj.myapplication.service.UserService;
import com.cjj.myapplication.service.serviceImpl.UserServiceImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

@RestController
@Slf4j
public class UserController implements UserAPI {
    @Value("${salt}")
    private String salt;
    UserConverter converter = UserConverter.INSTANCE;
    @Resource
    private UserService userService =new UserServiceImpl();
    @Override
    public List<User> selectAll() {
        List<User> list=userService.selectAll();
        return list;
    }

    @Override
    public ResponseData<LoginDTO> login(UserDTO userDTO) {
        User user = converter.userDTOToUser(userDTO);
        User userFromDB =userService.selectUserByNameAndPassword(user);
        LoginDTO loginDTO = new LoginDTO();
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(userFromDB.getId() + "")
                .setSubject(userFromDB.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, salt).
                setExpiration(new Date(System.currentTimeMillis() + 300000));

        if (userFromDB != null) {
            loginDTO.setToken(jwtBuilder.compact());
        }

        return new ResponseData<>(200, "success", loginDTO);
    }

}
