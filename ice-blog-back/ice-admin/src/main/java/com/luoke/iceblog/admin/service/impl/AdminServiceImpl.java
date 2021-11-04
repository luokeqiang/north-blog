package com.luoke.iceblog.admin.service.impl;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoke.iceblog.common.entity.Admin;
import com.luoke.iceblog.admin.mapper.AdminMapper;
import com.luoke.iceblog.admin.service.AdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.luoke.iceblog.common.util.SecurityUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author luoke
 * @since 2021-10-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements AdminService {
    @Resource
    AdminMapper adminMapper;

    @Override
    public IPage<Admin> listAdmin(Page<Admin> page, String name) {
//        List<Admin> admins = adminMapper.listAdmin(page, name);
//        System.out.println(admins);
//        Page<Admin> adminPage = new Page<>();
//        adminPage.setRecords(admins);
        return adminMapper.listAdmin(page, name);
    }

    @Override
    public boolean createAdmin(Admin admin) {
        admin.setPassword(SecurityUtils.encryptPassword(admin.getPassword()));
        int insert = adminMapper.insert(admin);
        System.out.println(insert);
        return insert == 1;
    }

    @Override
    public Boolean updateAdmin(Admin admin) {
        return null;
    }

    @Override
    public Boolean updateAdmin(Set<String> ids) {
        return null;
    }

    @Override
    public Admin getAdminByUsername(String username) {
        return adminMapper.getAdminByUsername(username);
    }
}
