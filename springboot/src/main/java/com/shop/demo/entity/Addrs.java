package com.shop.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 收货地址表
 */
@Data
public class Addrs implements Serializable {
    private static final long serialVersionUID = -41028793825024970L;

    @ApiModelProperty(value = "每页多少条", example = "10")
    private Integer limit;

    @ApiModelProperty(value = "第几页", example = "1")
    private Integer offset;

    @ApiModelProperty(value = "", example = " ")
    private Integer id;

    @ApiModelProperty(value = "收货地址", example = " ")
    private String addr;

    @ApiModelProperty(value = "收货人", example = " ")
    private String name;

    @ApiModelProperty(value = "联系电话", example = " ")
    private String tel;

    @ApiModelProperty(value = "关联id", example = " ")
    private Integer uid;

    @ApiModelProperty(value = "时间", example = " ")
    public String time;

    @ApiModelProperty(value = "是否默认收货地址 是 否", example = " ")
    private String isdefault;


}
