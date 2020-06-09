package com.whc.base_project.model;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.ToString;

import java.io.Serializable;
/**
 * @author whc
 */
@Data
@ToString
public class User implements Serializable {
    @ApiModelProperty(value = "用户id")
    private Integer id;

    @ApiModelProperty(value = "登录名")
    private String username;

    @ApiModelProperty(value = "密码")
    private String password;

    @ApiModelProperty(value = "权限enum")
    private String role;
}