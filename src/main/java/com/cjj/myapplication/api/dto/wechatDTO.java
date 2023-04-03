package com.cjj.myapplication.api.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class wechatDTO {
    private String appid;
    private String redirect_uri;
}
