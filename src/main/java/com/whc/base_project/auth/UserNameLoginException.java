package com.whc.base_project.auth;

import com.whc.base_project.exception.BaseException;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */

public class UserNameLoginException extends BaseException {

    public UserNameLoginException() {
        super(10001, "");
    }
}
