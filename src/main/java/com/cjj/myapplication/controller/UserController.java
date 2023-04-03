package com.cjj.myapplication.controller;

import com.cjj.myapplication.api.UserAPI;
import com.cjj.myapplication.api.dto.LoginDTO;
import com.cjj.myapplication.api.dto.UserDTO;
import com.cjj.myapplication.api.dto.WeiXinCheckUrl;
import com.cjj.myapplication.api.dto.wechatDTO;
import com.cjj.myapplication.common.PageUtils.PageRequest;
import com.cjj.myapplication.common.PageUtils.PageResult;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.common.utils.SHA1;
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
import java.util.*;

@RestController
@Slf4j
public class UserController implements UserAPI {
    @Value("${salt}")
    private String salt;
    UserConverter converter = UserConverter.INSTANCE;
    @Resource
    private UserService userService =new UserServiceImpl();


    @Override
    public ResponseData<PageResult> selectAllPageUser(PageRequest pageQuery) {
        PageResult pageResult =userService.findUserPage(pageQuery);
        ResponseData responseData=new ResponseData<>(0,"success",pageResult);
        return responseData;
    }

    @Override
    public ResponseData addUser(UserDTO userDTO) {
        User user=converter.UserDTOToUser(userDTO);
        if(user.getUsername()!=null&&user.getUsername()!=""){
            User usertest=selectUserByUserName(user.getUsername());
            if(usertest!=null){
                ResponseData responseData=new ResponseData<>(1,"UserExist!");
                return responseData;
            }
        }
        userService.addUser(user);
        ResponseData responseData=new ResponseData<>(0,"success");
        return responseData;
    }

    private User selectUserByUserName(String username) {
        User user=userService.selectUserByUserName(username);
        return user;
    }

    @Override
    public ResponseData deleteUser(UserDTO userDTO) {
        User user=converter.UserDTOToUser(userDTO);
        userService.deleteUser(user.getId());
        ResponseData responseData=new ResponseData<>(0,"success");
        return responseData;
    }

    @Override
    public ResponseData updateUser(UserDTO userDTO) {
        User user=converter.UserDTOToUser(userDTO);
        userService.updateUser(user);
        ResponseData responseData=new ResponseData(0,"success");
        return responseData;
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

    @Override
    public String checkUrl(WeiXinCheckUrl weiXinCheckUrl) {
            //与申请测试号网站填写的token对应
            String token = "cjj";
            List<String> paramList = new ArrayList<>();
            paramList.add(weiXinCheckUrl.getNonce());
            paramList.add(weiXinCheckUrl.getTimestamp());
            paramList.add(token);
            //按字节排序
            Collections.sort(paramList);
            //按顺序拼接字符串
            StringBuilder stringBuilder = new StringBuilder();
            paramList.forEach(stringBuilder::append);
            //sha1加密
            String encode = SHA1.encode(stringBuilder.toString());
            System.out.println("微信消息发过来了：" + weiXinCheckUrl.getEchostr());
            if (encode.equals(weiXinCheckUrl.getSignature())) {
                return weiXinCheckUrl.getEchostr();
            } else {
                return "";
            }
    }

    @Override
    public ResponseData getUrl() {
        wechatDTO wechatDTO=new wechatDTO("wx7496307a795a17e3","http://45e70d6d.r11.cpolar.top");
        return new ResponseData<>(200, "success", wechatDTO);
    }


}
