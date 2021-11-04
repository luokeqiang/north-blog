package com.luoke.iceblog.admin.security.handler;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luoke.iceblog.common.entity.Admin;
import com.luoke.iceblog.common.service.TokenService;
import com.luoke.iceblog.common.vo.AdminVo;
import com.luoke.iceblog.common.vo.ResponseVo;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 *  登录成功处理器
 * @author luoke
 * @date 2021/11/4 10:06
 */
@Component
public class LoginAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Resource
    TokenService tokenService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        Admin admin = (Admin) authentication.getPrincipal();
        AdminVo adminVo = new AdminVo();
        adminVo.setAdmin(admin);
        String token = tokenService.getToken(adminVo);
        adminVo.setToken(token);
        admin.setPassword(null);
        out.write(new ObjectMapper().writeValueAsString(ResponseVo.success("登录成功",adminVo)));
        out.flush();
        out.close();
    }
}
