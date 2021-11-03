package com.luoke.northblog.admin.service.impl;

import com.luoke.northblog.common.entity.Admin;
import com.luoke.northblog.admin.service.AdminService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 用户登录验证
 * @author luoke
 * @date 2021/11/3 11:16
 */
@Service
public class UserDetailsServiceImpl implements UserDetailsService {
    @Resource
    AdminService adminService;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Admin admin = adminService.getAdminByUsername(username);
        if (admin == null) {
            throw new UsernameNotFoundException("用户不存在");
        }
        return admin;
    }
}
