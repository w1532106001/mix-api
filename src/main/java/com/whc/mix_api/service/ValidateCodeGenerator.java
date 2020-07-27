package com.whc.mix_api.service;

import com.whc.mix_api.auth.model.ValidateCode;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whc
 * @date 2020/5/31 0:21
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(HttpServletRequest request);

}
