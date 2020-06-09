package com.whc.base_project.api;

import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * ApiResult构造器
 *
 * @author bianxinhuan
 */
public class ApiResultBuilder<T> {
    /**
     * 处理结果编码
     */
    private int code;

    /**
     * 处理消息
     */
    private String message;

    /**
     * 返回数据
     */
    private T data;

    public ApiResultBuilder<T> code(Integer code) {
        this.code = code;
        return this;
    }

    public ApiResultBuilder<T> message(String message) {
        this.message = message;
        return this;
    }

    public ApiResultBuilder<T> data(T data) {
        this.data = data;
        return this;
    }

    public ApiResult<T> build() {
        return new ApiResult<>(code, message, data);
    }

    public static ApiResult unauthorized() {
        return new ApiResult(ApiResultCode.UNAUTHORIZED);
    }

    public static ApiResult success() {
        return new ApiResult<>(ApiResultCode.SUCCESS, "操作成功");
    }

    public static <D> ApiResult<D> success(D data) {
        return new ApiResult<>(ApiResultCode.SUCCESS, data);
    }

    public static <D> ApiResult<D> success(D data, String message) {
        return new ApiResult<>(ApiResultCode.SUCCESS.getCode(), message, data);
    }

    public static <D> ApiResult<D> success(String messageFormat, Object... args) {
        String message = String.format(messageFormat, args);
        return new ApiResult<>(ApiResultCode.SUCCESS.getCode(), message, null);
    }

    public static <D> PagingApiResult<D> paging(List<D> list) {
        return new PagingApiResult<D>(list);
    }

    public static <D> PagingApiResult<D> paging(PageInfo pageInfo, List<D> list) {
        return new PagingApiResult<>(pageInfo, list);
    }

    public static ApiResult fail() {
        return new ApiResult<>(ApiResultCode.FAIL, ApiResultCode.FAIL.getDescription());
    }

    public static ApiResult fail(String message) {
        return new ApiResult<>(ApiResultCode.FAIL, message);
    }

    public static ApiResult fail(String message, Object... args) {
        return new ApiResult<>(ApiResultCode.FAIL, String.format(message, args));
    }
}
