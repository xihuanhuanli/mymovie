package com.cjj.myapplication.api;

import com.cjj.myapplication.api.dto.WeiXinCheckUrl;
import com.cjj.myapplication.common.ResponseData;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@RequestMapping(value = "/wechat")
public interface WechatAPI {
    @RequestMapping(value = "/checkUrl",method = {RequestMethod. POST ,RequestMethod. GET })
    String checkUrl( WeiXinCheckUrl weiXinCheckUrl);
    @RequestMapping(value = "/getUrl",method = RequestMethod.GET)
    ResponseData getUrl();

    //扫描二维码授权成功，取到code，回调方法
    @RequestMapping(value = "/pcAuth")
    @ResponseBody
    String pcCallback(String code, String state, HttpServletRequest request, HttpServletResponse response) throws Exception;

    @RequestMapping(value="/polling",method=RequestMethod.POST)
    @ResponseBody
    ResponseData polling(String sessionId,HttpServletRequest request,HttpServletResponse response);
}
