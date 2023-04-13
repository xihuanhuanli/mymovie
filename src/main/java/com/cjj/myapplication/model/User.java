package com.cjj.myapplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private int id;
    private String wechatid;
    private String username;
    private String password;
    private String avatar;
    private String role;
    private LocalDateTime createDate;
    private int createBy;
    private LocalDateTime updateDate;
    private int updateBy;
    private int isDeleted;
    private int version;

}
