package com.cjj.myapplication.api.dto;

import lombok.*;
import lombok.experimental.FieldDefaults;

import java.io.Serializable;

@FieldDefaults(level = AccessLevel.PRIVATE)
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WeiXinCheckUrl implements Serializable {

    private static final long serialVersionUID = 757097916620967707L;
    /**
     * 微信加密签名，signature结合了开发者填写的token参数和请求中的timestamp参数、nonce参数。
     */
    String signature;

    /**
     * 时间戳
     */
    String timestamp;

    /**
     * 随机数
     */
    String nonce;

    /**
     * 随机字符串
     */
    String echostr;
}