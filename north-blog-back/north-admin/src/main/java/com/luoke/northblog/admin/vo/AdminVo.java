package com.luoke.northblog.admin.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

import java.time.LocalDate;

/**
 *  Admin 管理员 Vo 对象
 * @author luoke
 * @date 2021/11/3 10:06
 */
public class AdminVo {
    @ApiModelProperty("用户名")
    private String userName;

    @ApiModelProperty("性别")
    private String gender;

    @ApiModelProperty("联系电话")
    private String phone;

    @ApiModelProperty("邮箱")
    private String email;

    @ApiModelProperty("出生日期")
    private LocalDate birthday;
}
