package com.shop.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 订单表
 */
@Data
public class Orders implements Serializable {
    private static final long serialVersionUID = -30050671733820358L;

    @ApiModelProperty(value = "每页多少条", example = "10")
    private Integer limit;

    @ApiModelProperty(value = "第几页", example = "1")
    private Integer offset;

    @ApiModelProperty(value = "", example = " ")
    private Integer id;

    @ApiModelProperty(value = "订单号", example = " ")
    private String code;

    @ApiModelProperty(value = "关联用户id", example = " ")
    private Integer uid;

    @ApiModelProperty(value = "时间", example = " ")
    public String time;

    @ApiModelProperty(value = "商品名", example = " ")
    private String gname;

    @ApiModelProperty(value = "数量", example = " ")
    private Integer num;

    @ApiModelProperty(value = "价格", example = " ")
    private Double price;

    @ApiModelProperty(value = "商品图片", example = " ")
    private String img;

    @ApiModelProperty(value = "收货地址", example = " ")
    private String saddr;

    @ApiModelProperty(value = "状态", example = " ")
    private String status;

    @ApiModelProperty(value = "关联商品id", example = " ")
    private Integer gid;

    @ApiModelProperty(value = "收货电话", example = " ")
    private String stel;

    @ApiModelProperty(value = "收货人", example = " ")
    private String sname;

    @ApiModelProperty(value = "商品分类", example = " ")
    private String type;


}
