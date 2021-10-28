package com.luoke.northblog.admin.controller;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

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
@Api(value = "管理员接口")
public class AdminController {

    @GetMapping("/hello")
    @ApiOperation("测试")
    public String test() {
        return "Hello world" + LocalDateTime.now();
    }
}

