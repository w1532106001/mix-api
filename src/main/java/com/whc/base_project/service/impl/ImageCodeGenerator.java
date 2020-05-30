package com.whc.base_project.service.impl;

import cn.hutool.captcha.CaptchaUtil;
import cn.hutool.captcha.LineCaptcha;
import com.whc.base_project.auth.model.ImageCode;
import com.whc.base_project.auth.model.ValidateCode;
import com.whc.base_project.service.ValidateCodeGenerator;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whc
 * @date 2020/5/31 0:22
 * 图片验证码生成器
 */

@Component("imageCodeGenerator")
public class ImageCodeGenerator implements ValidateCodeGenerator {

    /**
     * 生成图形验证码
     * @param request
     * @return
     */
    @Override
    public ValidateCode generate(HttpServletRequest request) {
        LineCaptcha lineCaptcha = CaptchaUtil.createLineCaptcha(200, 100);
        return new ImageCode(lineCaptcha.getImage(), lineCaptcha.getCode(), 600);
    }
}
