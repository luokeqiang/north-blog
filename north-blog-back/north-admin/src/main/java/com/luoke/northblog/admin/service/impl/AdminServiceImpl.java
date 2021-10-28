package com.luoke.northblog.admin.service.impl;

import com.luoke.northblog.admin.entity.Admin;
import com.luoke.northblog.admin.mapper.AdminMapper;
import com.luoke.northblog.admin.service.IAdminService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 管理员表 服务实现类
 * </p>
 *
 * @author luoke
 * @since 2021-10-28
 */
@Service
public class AdminServiceImpl extends ServiceImpl<AdminMapper, Admin> implements IAdminService {

}
