package com.luoke.northblog.admin.security.filter;

import cn.hutool.core.util.ObjectUtil;
import com.luoke.northblog.common.service.TokenService;
import com.luoke.northblog.common.vo.AdminVo;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 *  token 认证过滤器
 * @author luoke
 * @date 2021/11/3 15:08
 */
@Component
public class TokenFilter extends OncePerRequestFilter {
    @Resource
    TokenService tokenService;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        AdminVo user = tokenService.getUser(request);
        if (ObjectUtil.isNotNull(user) && ObjectUtil.isNull(SecurityContextHolder.getContext().getAuthentication())) {
            UsernamePasswordAuthenticationToken authenticationToken = new UsernamePasswordAuthenticationToken(user.getAdmin().getUsername(), null,user.getAdmin().getAuthorities());
            authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
            SecurityContextHolder.getContext().setAuthentication(authenticationToken);
        }
        filterChain.doFilter(request, response);
    }
}
