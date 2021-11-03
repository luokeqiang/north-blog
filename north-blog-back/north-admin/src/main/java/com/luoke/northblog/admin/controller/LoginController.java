package com.luoke.northblog.admin.controller;

import com.luoke.northblog.common.service.TokenService;
import com.luoke.northblog.common.vo.ResponseVo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 *  登录相关的controller接口
 * @author luoke
 * @date 2021/11/2 15:48
 */
@RestController
@RequestMapping("/auth")
@Api(tags = "后端登录接口")
public class LoginController {
    @Resource
    TokenService tokenService;

    @GetMapping("/info")
    @ApiOperation("获取用户信息")
    public ResponseVo login(String token) {
        return ResponseVo.success("获取成功",tokenService.getUser(token));
    }
}
