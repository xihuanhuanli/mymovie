package com.cjj.myapplication.controller;

import com.alibaba.fastjson.JSONObject;
import com.cjj.myapplication.api.WechatAPI;
import com.cjj.myapplication.api.dto.LoginDTO;
import com.cjj.myapplication.api.dto.WeiXinCheckUrl;
import com.cjj.myapplication.api.dto.wechatDTO;
import com.cjj.myapplication.common.ResponseData;
import com.cjj.myapplication.common.utils.SHA1;

import com.cjj.myapplication.model.User;
import com.cjj.myapplication.service.UserService;
import com.cjj.myapplication.service.WechatService;
import com.cjj.myapplication.service.serviceImpl.UserServiceImpl;
import com.cjj.myapplication.service.serviceImpl.WechatServiceImpl;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.*;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class WechatController implements WechatAPI {
    @Value("${salt}")
    private String salt;
    public Logger logger= LoggerFactory.getLogger(this.getClass());
    @Resource
    private UserService userService=new UserServiceImpl();
    @Resource
    private RedisTemplate<String, Object> redisTemplate;
    @Resource
    private WechatService wechatService=new WechatServiceImpl();
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

    @Override
    public String pcCallback(String code, String state, HttpServletRequest request, HttpServletResponse response) throws Exception {
        String result = wechatService.getAccessToken(code);//根据code获取access_token和openId，不懂看微信文档
            JSONObject jsonObject = JSONObject.parseObject(result);
        //String refresh_token = jsonObject.getString("refresh_token");
            String access_token = jsonObject.getString("access_token");
            String openId = jsonObject.getString("openId");
            logger.info("------------授权成功----------------");
            JSONObject infoJson = wechatService.getUserInfo(access_token,openId);//根据token和openId获取微信用户信息，不懂看我上一篇文章开始分享的链接
            if(infoJson!=null){
                String nickname = infoJson.getString("nickName");
                logger.info("-----nickname-----"+nickname);
                logger.info("-----sessionId-----"+state);
                infoJson.put("openId", openId);
                redisTemplate.opsForValue().set(state, infoJson, 10*60, TimeUnit.SECONDS);
                return "登录成功！";
            }
            return "登录失败！";
    }

    @Override
    public ResponseData polling(String sessionId, HttpServletRequest request, HttpServletResponse response) {
        if(redisTemplate.hasKey(sessionId)){
            JSONObject infoJson = (JSONObject)redisTemplate.opsForValue().get(sessionId);
            redisTemplate.opsForValue().getOperations().delete(sessionId);
            String openId = (String)infoJson.get("openId");
            //根据openId判断我们网站是否存在该用户，数据库用户表会保存用户
            User user = userService.getUserInfoByWechatId(openId);
            if (user == null) {
                String nickname = (String)infoJson.get("nickName");
                String avatar = (String) infoJson.get("avatar");
//                headimgurl.replaceAll( "\\\\","");
                String role = "editor";
                User newuser = new User();
                newuser.setRole(role);
                newuser.setWechatid(openId);
                newuser.setUsername(nickname);
                newuser.setAvatar(avatar);
                userService.addUser(newuser);//新增用户
                user=userService.getUserInfoByWechatId(openId);
                if(user==null){
                    return new ResponseData<>(500, "登录失败");
                }
            }
            //登录操作
            try {
                LoginDTO loginDTO = new LoginDTO();
                if (user != null) {
                    JwtBuilder jwtBuilder = Jwts.builder()
                            .setId(user.getId() + "")
                            .setSubject(user.getUsername())
                            .setIssuedAt(new Date())
                            .signWith(SignatureAlgorithm.HS256, salt).
                            setExpiration(new Date(System.currentTimeMillis() + 1200000));
                    loginDTO.setToken(jwtBuilder.compact());
                    return new ResponseData<>(200, "success", loginDTO);
                }else {
                    return new ResponseData<>(0, "user not exist");
                }
            } catch (Exception e) {
                return new ResponseData<>(0, "error", null);
            }
        }else{//not has key
            return new ResponseData<>(0, "not has key in redis", null);
        }
    }
}
