package com.cjj.myapplication.common;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ResponseData<T> {
    private int code;
    private String message;
    private T data;

    public ResponseData(int code,String message){
        this(code,message,null);

    }

}
