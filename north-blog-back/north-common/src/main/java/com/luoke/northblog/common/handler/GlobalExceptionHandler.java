package com.luoke.northblog.common.handler;

import com.luoke.northblog.common.vo.ResponseVo;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 *  全局异常处理器
 * @author luoke
 * @date 2021/11/3 12:37
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(value = Exception.class)
    public ResponseVo handlerException(Exception e) {
        e.printStackTrace();
        return ResponseVo.error(e.getMessage());
    }
}
