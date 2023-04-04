package com.cjj.myapplication.api.dto;

import lombok.Data;

@Data
public class UserDTO {
    private int id;
    private String wechatid;

    private String username;

    private String password;

    private String avatar;

    private String role;
}
