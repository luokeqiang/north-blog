package com.luoke.northblog.admin.mapper;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoke.northblog.common.entity.Admin;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

/**
 * <p>
 * 管理员表 Mapper 接口
 * </p>
 *
 * @author luoke
 * @since 2021-10-28
 */
public interface AdminMapper extends BaseMapper<Admin> {
    /**
     *  分页查询管理员列表
     * @param page 分页对象
     * @param name 管理员姓名
     * @return 管理员列表
     */
    IPage<Admin> listAdmin(Page<Admin> page,String name);

    /**
     *  通过用户名查询用户信息
     * @param username 用户名
     * @return Admin 管理员信息
     */
    @Select("select * from admin where user_name=#{username}")
    Admin getAdminByUsername(String username);
}
