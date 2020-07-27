package com.whc.mix_api.service.impl;

import com.whc.mix_api.auth.model.ValidateCode;
import com.whc.mix_api.service.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whc
 * @date 2020/5/31 0:25
 */

// 短信验证码生成器
@Component("smsCodeGenerator")
class SmsCodeGenerator implements ValidateCodeGenerator {

    @Override
    public ValidateCode generate(HttpServletRequest request) {
        String code = RandomStringUtils.randomNumeric(6);
        return new ValidateCode(code, 600);
    }

}
