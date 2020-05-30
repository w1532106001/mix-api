package com.whc.base_project.service.impl;

import com.whc.base_project.auth.model.ImageCode;
import com.whc.base_project.auth.model.ValidateCode;
import com.whc.base_project.service.ValidateCodeGenerator;
import org.apache.commons.lang3.RandomStringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

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
