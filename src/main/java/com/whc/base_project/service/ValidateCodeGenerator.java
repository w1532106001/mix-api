package com.whc.base_project.service;

import com.whc.base_project.auth.model.ValidateCode;
import org.springframework.web.context.request.ServletWebRequest;

import javax.servlet.http.HttpServletRequest;

/**
 * @author whc
 * @date 2020/5/31 0:21
 */
public interface ValidateCodeGenerator {
    ValidateCode generate(HttpServletRequest request);

}
