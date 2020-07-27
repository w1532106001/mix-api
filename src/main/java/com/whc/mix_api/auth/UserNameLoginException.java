package com.whc.mix_api.auth;

import com.whc.mix_api.exception.BaseException;

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
