package com.whc.base_project.auth;

import lombok.Getter;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
public enum LoginEnum {
    USERNAME(1),
    SMS(2);

    private int loginType;

    LoginEnum(int loginType) {
        this.loginType = loginType;
    }

    public int getLoginType() {
        return loginType;
    }
}
