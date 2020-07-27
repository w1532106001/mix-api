package com.whc.mix_api.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author whc
 * @date 2020/5/29
 * @description 用户接口
 */
public interface IUserService {
    /**
     * 根据登录名获取登录验证码
     * @param loginName 用户随机生成的uuid
     * @param request 请求
     * @param response 响应
     */
    void getLoginCaptcha(String loginName, HttpServletRequest request, HttpServletResponse response) throws Exception;

    /**
     * 发送短信验证码
     * @param mobile 手机号
     * @param code 图片验证码
     */
    void send(String mobile,String code);
}
