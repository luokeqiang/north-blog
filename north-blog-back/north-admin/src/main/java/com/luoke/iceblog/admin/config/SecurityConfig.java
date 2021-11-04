package com.luoke.iceblog.admin.config;

import com.luoke.iceblog.admin.security.filter.TokenFilter;
import com.luoke.iceblog.admin.security.handler.LoginAuthenticationFailureHandler;
import com.luoke.iceblog.admin.security.handler.LoginAuthenticationSuccessHandler;
import com.luoke.iceblog.admin.security.filter.LoginFilter;
import com.luoke.iceblog.admin.security.handler.LogoutSuccessHandlerImpl;
import com.luoke.iceblog.admin.security.handler.UnauthorizedHandler;
import com.luoke.iceblog.common.service.TokenService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.annotation.Resource;

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

    /**
     *  登录成功处理器
     */
    @Resource
    private LoginAuthenticationSuccessHandler loginAuthenticationSuccessHandler;
    /**
     *  登录失败处理器
     */
    @Resource
    private LoginAuthenticationFailureHandler loginAuthenticationFailureHandler;

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
        // 登录成功处理器
        loginFilter.setAuthenticationSuccessHandler(loginAuthenticationSuccessHandler);
        // 登录失败处理器
        loginFilter.setAuthenticationFailureHandler(loginAuthenticationFailureHandler);
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
