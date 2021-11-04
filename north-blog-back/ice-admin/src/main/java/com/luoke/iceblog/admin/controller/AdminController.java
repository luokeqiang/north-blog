package com.luoke.iceblog.admin.controller;


import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.luoke.iceblog.common.entity.Admin;
import com.luoke.iceblog.admin.service.AdminService;
import com.luoke.iceblog.common.pojo.bo.PageBo;
import com.luoke.iceblog.common.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <p>
 * 管理员表 前端控制器
 * </p>
 *
 * @author luoke
 * @since 2021-10-28
 */
@RestController
@RequestMapping("/admin")
@Api(tags = "管理员接口")
public class AdminController {
    @Resource
    AdminService adminService;

    @GetMapping("/list")
    @ApiOperation("分页查询管理员列表")
    public ResponseVo listAdmin(PageBo pageBo, String name) {
        return ResponseVo.success("查询成功", adminService.listAdmin(new Page<>(pageBo.getCurrent(), pageBo.getSize()), name));
    }

    @PostMapping("/create")
    @ApiOperation("新增管理员用户")
    public ResponseVo createAdmin(Admin admin) {
        boolean create = adminService.createAdmin(admin);
        return create ? ResponseVo.success("新增用户成功",admin) : ResponseVo.error("新增用户失败");
    }
}

