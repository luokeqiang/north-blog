package com.luoke.iceblog.admin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luoke.iceblog.common.vo.ResponseVo;
import org.springframework.security.authentication.*;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @author luoke
 * @date 2021/11/4 10:10
 */
@Component
public class LoginAuthenticationFailureHandler implements AuthenticationFailureHandler {
    @Override
    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        ResponseVo responseVo = ResponseVo.error(exception.getMessage());
        if (exception instanceof LockedException) {
            responseVo.setMsg("账户被锁定，请联系管理员!");
        } else if (exception instanceof CredentialsExpiredException) {
            responseVo.setMsg("密码过期，请联系管理员!");
        } else if (exception instanceof AccountExpiredException) {
            responseVo.setMsg("账户过期，请联系管理员!");
        } else if (exception instanceof DisabledException) {
            responseVo.setMsg("账户被禁用，请联系管理员!");
        } else if (exception instanceof BadCredentialsException) {
            responseVo.setMsg("用户名或者密码输入错误，请重新输入!");
        }
        out.write(new ObjectMapper().writeValueAsString(responseVo));
        out.flush();
        out.close();
    }
}
