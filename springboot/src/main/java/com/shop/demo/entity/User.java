package com.shop.demo.entity;

import java.io.Serializable;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 用户表
 */
@Data
public class User implements Serializable {
    private static final long serialVersionUID = -13398005194243544L;

    @ApiModelProperty(value = "每页多少条", example = "10")
    private Integer limit;

    @ApiModelProperty(value = "第几页", example = "1")
    private Integer offset;

    @ApiModelProperty(value = "", example = " ")
    private Integer id;

    @ApiModelProperty(value = "账号", example = " ")
    private String user;

    @ApiModelProperty(value = "密码", example = " ")
    private String pwd;

    @ApiModelProperty(value = "名称", example = " ")
    private String name;

    @ApiModelProperty(value = "时间", example = " ")
    public String time;

    @ApiModelProperty(value = "手机号", example = " ")
    private String tel;

    @ApiModelProperty(value = "头像", example = " ")
    private String img;

    @ApiModelProperty(value = "密码提示问题", example = " ")
    private String question;

    @ApiModelProperty(value = "答案", example = " ")
    private String answer;

    @ApiModelProperty(value = "居住地", example = " ")
    private String addr;

    @ApiModelProperty(value = "性别 男 女", example = " ")
    private String sex;


}
