package com.luoke.iceblog.common.pojo.bo;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 *  分页实体
 * @author luoke
 * @date 2021/10/29 12:25
 */
@ApiModel(description = "分页实体对象")
@Data
public class PageBo {
    @ApiModelProperty(value = "当前页码")
    private long current;
    @ApiModelProperty(value = "每页显示数量")
    private long size = 10L;
    private long total;
    private List<?> records;
}
