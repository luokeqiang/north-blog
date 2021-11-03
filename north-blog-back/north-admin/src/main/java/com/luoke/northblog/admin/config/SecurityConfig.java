package com.luoke.northblog.admin.config;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.luoke.northblog.admin.security.filter.TokenFilter;
import com.luoke.northblog.common.entity.Admin;
import com.luoke.northblog.admin.security.filter.LoginFilter;
import com.luoke.northblog.admin.security.handler.LogoutSuccessHandlerImpl;
import com.luoke.northblog.admin.security.handler.UnauthorizedHandler;
import com.luoke.northblog.common.service.TokenService;
import com.luoke.northblog.common.vo.AdminVo;
import com.luoke.northblog.common.vo.ResponseVo;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.*;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;
import java.io.PrintWriter;

/**
 *  Spring Security 配置文件
 * @author luoke
 * @date 2021/11/2 16:42
 */
@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    @Resource
    TokenService tokenService;
    @Resource
    private UnauthorizedHandler unauthorizedHandler;

    @Resource
    private LogoutSuccessHandlerImpl logoutSuccessHandler;

    @Resource
    private UserDetailsService userDetailsService;

    @Resource
    TokenFilter tokenFilter;

    /**
     *  允许匿名访问的URI
     */
    private final String[] anonymousRequest = new String[]{"/auth/login", "/captchaImage","/swagger-ui/","/swagger-ui.html","/swagger-resources/**","/v3/api-docs"};
    /**
     *  允许 任何人 访问的URI
     */
    private final String[] permitAllRequest = new String[]{  "/*.html","/**/*.png",
            "/**/*.html",
            "/**/*.css",
            "/**/*.js"};

    @Bean
    public LoginFilter loginFilter() throws Exception {
        LoginFilter loginFilter = new LoginFilter();
        loginFilter.setAuthenticationSuccessHandler((request, response, authentication) -> {
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
        );
        loginFilter.setAuthenticationFailureHandler((request, response, exception) -> {
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
        );
        loginFilter.setAuthenticationManager(authenticationManagerBean());
        loginFilter.setFilterProcessesUrl("/auth/login");
        return loginFilter;
    }
    @Override
    protected void configure(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                // CSRF禁用，因为不使用session
                .csrf().disable()
                // 认证失败处理类
                .exceptionHandling().authenticationEntryPoint(unauthorizedHandler).and()
                // 基于token，所以不需要session
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS).and()
                // 过滤请求
                .authorizeRequests()
                // 允许 permitAllRequest 中的请求被任何人访问
                .antMatchers(HttpMethod.GET,permitAllRequest).permitAll()
                // 对 anonymousRequest 中的 URI 允许匿名访问
                .antMatchers(anonymousRequest).anonymous()
                // 除上面外的所有请求全部需要鉴权认证
                .anyRequest()
                .authenticated()
                .and()
                .headers().frameOptions().disable()
                .and()
                .logout()
                .logoutSuccessHandler(logoutSuccessHandler);
        httpSecurity.addFilterAt(loginFilter(), UsernamePasswordAuthenticationFilter.class);
        httpSecurity.addFilterBefore(tokenFilter,UsernamePasswordAuthenticationFilter.class);

    }

    /**
     * 强散列哈希加密实现
     */
    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder()
    {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
                .userDetailsService(userDetailsService)
                .passwordEncoder(bCryptPasswordEncoder());
    }
}
