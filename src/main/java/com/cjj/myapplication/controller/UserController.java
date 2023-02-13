package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.UserAPI;
import com.cjj.myapplication.api.dto.LoginDTO;
import com.cjj.myapplication.api.dto.UserDTO;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.converter.UserConverter;
import com.cjj.myapplication.model.User;
import com.cjj.myapplication.service.UserService;
import com.cjj.myapplication.service.serviceImpl.UserServiceImpl;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
        User user = converter.UserDTOToUser(userDTO);
        User userFromDB =userService.selectUserByNameAndPassword(user);
        LoginDTO loginDTO = new LoginDTO();
        if (userFromDB != null) {
        JwtBuilder jwtBuilder = Jwts.builder()
                .setId(userFromDB.getId() + "")
                .setSubject(userFromDB.getUsername())
                .setIssuedAt(new Date())
                .signWith(SignatureAlgorithm.HS256, salt).
                setExpiration(new Date(System.currentTimeMillis() + 300000));
                loginDTO.setToken(jwtBuilder.compact());
            return new ResponseData<>(200, "success", loginDTO);
        }

        return new ResponseData<>(0, "用户名或密码错误", null);
    }

    @Override
    public ResponseData<UserDTO> getUserInfo(Map<String, String> map) {
        String token = map.get("token");
        UserDTO userDTO=new UserDTO();
        if (token!=null) {
            Claims claims=null;
           try { claims = Jwts.parser().setSigningKey(salt).parseClaimsJws(token).getBody();
           }catch (Exception e){
               return new ResponseData<>(401, "token过期", null);
           }
            String userid = claims.getId();
            User user=userService.getUserInfoById(Integer.valueOf(userid));
            userDTO=converter.UserToUserDTO(user);
        }
        return new ResponseData<>(200, "success", userDTO);
    }

    @Override
    public ResponseData<UserDTO> logout() {
        return new ResponseData<>(200, "success", null);
    }


}
