package com.whc.base_project.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Api返回结果码枚举
 *
 * @author bianxinhuan
 */
@ApiModel("Api返回结果码枚举")
public enum ApiResultCode {

    /**
     * 操作失败
     */
    @ApiModelProperty("操作失败")
    FAIL(-1, "操作失败"),

    /**
     * 未登录或token已失效
     */
    @ApiModelProperty("未登录或token已失效")
    UNAUTHORIZED(0, "未登录或token已失效"),

    /**
     * 操作成功
     */
    @ApiModelProperty("操作成功")
    SUCCESS(1, "操作成功"),
    ;

    ApiResultCode(int code, String description) {
        this.code = code;
        this.description = description;
    }

    /**
     * 代码
     */
    private int code;

    /**
     * 描述
     */
    private String description;

    public int getCode() {
        return code;
    }

    public String getDescription() {
        return description;
    }

}
