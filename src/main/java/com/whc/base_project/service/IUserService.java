package com.whc.base_project.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author whc
 * @date 2020/5/29
 * @description 用户接口
 */
public interface IUserService {
    /**
     * 根据uuid获取登录验证码
     * @param uuid 用户随机生成的uuid
     * @param request 请求
     * @param response 响应
     */
    void getLoginCaptcha(String uuid, HttpServletRequest request, HttpServletResponse response) throws Exception;
}
