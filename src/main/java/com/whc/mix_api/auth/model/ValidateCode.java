package com.whc.mix_api.auth.model;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * @author whc
 * @date 2020/5/31 0:18
 */


@Data
public class ValidateCode {

    private String code;

    private LocalDateTime expireTime;

    public ValidateCode(String code, int expireIn) {
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireIn);
    }

    public boolean isExpired() {
        return LocalDateTime.now().isAfter(getExpireTime());
    }

    public ValidateCode(String code, LocalDateTime expireTime) {
        super();
        this.code = code;
        this.expireTime = expireTime;
    }
}
