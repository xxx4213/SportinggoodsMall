package com.shop.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 评论表
 */
@Data
public class Commit implements Serializable {
    private static final long serialVersionUID = -27963510282543654L;

    @ApiModelProperty(value = "每页多少条", example = "10")
    private Integer limit;

    @ApiModelProperty(value = "第几页", example = "1")
    private Integer offset;

    @ApiModelProperty(value = "", example = " ")
    private Integer id;

    @ApiModelProperty(value = "评论用户id", example = " ")
    private Integer uid;

    @ApiModelProperty(value = "评论内容", example = " ")
    private String content;

    @ApiModelProperty(value = "评论图片", example = " ")
    private String img;

    @ApiModelProperty(value = "时间", example = " ")
    public String time;

    @ApiModelProperty(value = "评论用户", example = " ")
    public String uname;

    @ApiModelProperty(value = "评论订单", example = " ")
    private String code;

    @ApiModelProperty(value = "关联商品id", example = " ")
    private Integer gid;


}
