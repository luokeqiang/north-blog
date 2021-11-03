package com.luoke.northblog.common.vo;

import cn.hutool.http.HttpStatus;
import lombok.Data;

import java.io.Serializable;

/**
 *  前端统一返回格式
 * @author luoke
 * @date 2021/10/29 11:46
 */
@Data
public class ResponseVo implements Serializable {
    private int code;
    private String msg;
    private Object data;


    public ResponseVo(int code, String msg, Object data) {
        this.code = code;
        this.msg = msg;
        this.data = data;
    }

    public static ResponseVo instance(int code, String msg, Object data) {
        return new ResponseVo(code, msg, data);
    }
    public static ResponseVo instance(int code, String msg) {
        return instance(code, msg, null);
    }

    public static ResponseVo success(String msg, Object data) {
        return new ResponseVo(HttpStatus.HTTP_OK,msg,data);
    }
    public static ResponseVo success(String msg) {
        return new ResponseVo(HttpStatus.HTTP_OK,msg,null);
    }

    public static ResponseVo error(String message){
        return new ResponseVo(HttpStatus.HTTP_INTERNAL_ERROR,message,null);
    }
}
