package com.luoke.iceblog.common.vo;

import com.luoke.iceblog.common.entity.Admin;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;


/**
 *  Admin 管理员 Vo 对象
 * @author luoke
 * @date 2021/11/3 10:06
 */
@Data
public class AdminVo extends BaseUserVo {
    @ApiModelProperty("是否启用")
    private Integer isEnabled;

    private Admin admin;
}


