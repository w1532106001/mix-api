package com.whc.mix_api.api;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 * Api返回结果
 *
 * @author bianxinhuan
 */
@ApiModel
public class ApiResult<T> {

    /**
     * 处理结果编码
     */
    @ApiModelProperty(value = "处理结果编码: -1 '操作失败'; 0 '未登录或token已失效'; 1 '操作成功'")
    protected int code;

    /**
     * 处理消息
     */
    @ApiModelProperty("处理消息")
    protected String message;

    /**
     * 返回数据
     */
    @ApiModelProperty("返回数据, 可以是对象/数组/字符串等")
    protected T data;

    public ApiResult(int code, String message, T data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public ApiResult(ApiResultCode apiResultCode, T data) {
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getDescription();
        this.data = data;
    }

    public ApiResult(ApiResultCode apiResultCode, String message) {
        this.code = apiResultCode.getCode();
        this.message = message;
        this.data = null;
    }

    public ApiResult(ApiResultCode apiResultCode) {
        this.code = apiResultCode.getCode();
        this.message = apiResultCode.getDescription();
        this.data = null;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}

