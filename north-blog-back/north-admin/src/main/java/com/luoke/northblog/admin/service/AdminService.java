package com.luoke.northblog.admin.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoke.northblog.common.entity.Admin;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Set;

/**
 * <p>
 * 管理员表 服务类
 * </p>
 *
 * @author luoke
 * @since 2021-10-28
 */
public interface AdminService extends IService<Admin> {
    /**
     *  分页查询管理员列表
     * @param page 分页对象
     * @param name 管理员姓名
     * @return 管理员列表
     */
    IPage<Admin> listAdmin(Page<Admin> page, String name);

    /**
     *  新增管理员用户
     * @param admin 管理员信息
     * @return true: 新增成功，false: 新增失败
     */
    boolean createAdmin(Admin admin);

    /**
     *  更新管理员用户
     * @param admin 管理员信息
     * @return true: 更新成功，false: 更新失败
     */
    Boolean updateAdmin(Admin admin);

    /**
     *  删除管理员
     * @param ids 要删除的管理员id集合，{@link Set}
     * @return true: 删除成功，false: 删除失败
     */
    Boolean updateAdmin(Set<String> ids);

    /**
     *  通过用户名查询用户信息
     * @param username 用户名
     * @return Admin 管理员信息
     */
    Admin getAdminByUsername(String username);
}
