package com.luoke.iceblog.admin.security.handler;

import cn.hutool.http.HttpStatus;
import com.luoke.iceblog.common.util.ServletUtil;
import com.luoke.iceblog.common.vo.ResponseVo;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.Serializable;

/**
 * @author luoke
 * @date 2021/11/2 16:59
 */
@Component
public class UnauthorizedHandler implements AuthenticationEntryPoint, Serializable {
    @Override
    public void commence(HttpServletRequest request, HttpServletResponse response, AuthenticationException authException) throws IOException, ServletException {
        ServletUtil.renderJsonString(HttpStatus.HTTP_UNAUTHORIZED, ResponseVo.instance(HttpStatus.HTTP_UNAUTHORIZED,"未登录或登录已过期或没有权限"),response);
    }
}
