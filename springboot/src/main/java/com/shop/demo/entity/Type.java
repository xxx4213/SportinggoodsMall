package com.shop.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 商品分类表
 */
@Data
public class Type implements Serializable {
    private static final long serialVersionUID = 251652595365852364L;

    @ApiModelProperty(value = "每页多少条", example = "10")
    private Integer limit;

    @ApiModelProperty(value = "第几页", example = "1")
    private Integer offset;

    @ApiModelProperty(value = "", example = " ")
    private Integer id;

    @ApiModelProperty(value = "分类名称", example = " ")
    private String name;

    @ApiModelProperty(value = "时间", example = " ")
    public String time;


}
