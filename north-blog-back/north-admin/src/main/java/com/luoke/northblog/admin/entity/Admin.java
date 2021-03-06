package com.luoke.northblog.admin.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.luoke.northblog.common.entity.BaseEntity;
import java.io.Serializable;
import java.time.LocalDate;
import java.time.LocalDateTime;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 管理员表
 * </p>
 *
 * @author luoke
 * @since 2021-10-28
 */
@Getter
@Setter
@TableName("admin")
@ApiModel(value = "Admin对象", description = "管理员表")
public class Admin extends BaseEntity<Admin> {

    private static final long serialVersionUID = 1L;

    @ApiModelProperty("用户名")
    @TableField("user_name")
    private String userName;

    @ApiModelProperty("性别")
    @TableField("gender")
    private String gender;

    @ApiModelProperty("登录密码")
    @TableField("password")
    private String password;

    @ApiModelProperty("联系电话")
    @TableField("phone")
    private String phone;

    @ApiModelProperty("邮箱")
    @TableField("email")
    private String email;

    @ApiModelProperty("出生日期")
    @TableField("birthday")
    private LocalDate birthday;

    @ApiModelProperty("微信号")
    @TableField("we_chat")
    private String weChat;

    @ApiModelProperty("QQ号")
    @TableField("qq")
    private String qq;

    @ApiModelProperty("最后登录时间")
    @TableField("last_login_time")
    private LocalDateTime lastLoginTime;

    @ApiModelProperty("最后登录IP")
    @TableField("last_login_ip")
    private Integer lastLoginIp;

    @ApiModelProperty("是否启用")
    @TableField("is_enabled")
    private Integer isEnabled;
}
