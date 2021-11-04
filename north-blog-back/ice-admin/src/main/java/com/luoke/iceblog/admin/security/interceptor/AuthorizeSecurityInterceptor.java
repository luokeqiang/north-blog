package com.luoke.iceblog.admin.security.interceptor;

import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 自定义授权类
 *
 * @author luoke
 * @date 2021/11/3 23:40
 */
@Component
public class AuthorizeSecurityInterceptor /*extends FilterSecurityInterceptor */{
    @Resource
    AuthorizeSecurityMetadataSource authorizeSecurityMetadataSource;

//    @Override
    public SecurityMetadataSource obtainSecurityMetadataSource() {
        return this.authorizeSecurityMetadataSource;
    }
}
