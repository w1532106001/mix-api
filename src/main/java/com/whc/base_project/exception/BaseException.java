package com.whc.base_project.exception;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */

public abstract class BaseException {
    private int code;
    private String message;

    public BaseException(int code, String message) {
        this.code = code;
        this.message = message;
    }
}
