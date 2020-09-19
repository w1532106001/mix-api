package com.whc.mix_api.auth;

import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.whc.mix_api.mapper.UserMapper;
import com.whc.mix_api.model.User;
import com.whc.mix_api.utils.RedisUtil;
import com.whc.mix_api.utils.TokenUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.UUID;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
@Component
@Slf4j
public class CustomAuthenticationSuccessHandler implements AuthenticationSuccessHandler {
    @Autowired
    private ObjectMapper objectMapper;
    @Resource
    private RedisUtil redisUtil;
    @Resource
    private UserMapper userMapper;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {
        log.info("登录成功");
        String loginType = request.getParameter("loginType");
        User user = null;
        switch (loginType) {
            case "1":
                user = userMapper.selectByUserName(request.getParameter("loginName"));
                break;
            case "2":
                user = userMapper.selectByMobile(request.getParameter("loginName"));
                break;
            default:
                break;
        }
        String token = TokenUtil.TOKEN_PREFIX + UUID.randomUUID().toString();
        redisUtil.getJedis().hset("LoginToken", token, JSONObject.toJSONString(user));
        response.setHeader(TokenUtil.TOKEN_HEADER, token);
        response.setContentType("application/json;charset=UTF-8");
        response.getWriter().write(objectMapper.writeValueAsString(authentication));
    }
}
