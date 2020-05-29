package com.whc.base_project.config;

import com.alibaba.fastjson.JSONObject;
import com.whc.base_project.config.model.JwtUser;
import com.whc.base_project.utils.JwtTokenUtil;
import com.whc.base_project.utils.RedisUtil;
import com.whc.base_project.utils.SpringContextUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.rememberme.InvalidCookieException;
import org.springframework.util.StreamUtils;
import org.springframework.util.StringUtils;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
@Slf4j
public class JWTAuthenticationFilter extends UsernamePasswordAuthenticationFilter {
    private AuthenticationManager authenticationManager;
    private RedisUtil redisUtil;


    public JWTAuthenticationFilter(AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
        super.setFilterProcessesUrl("/auth/login");
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request,
                                                HttpServletResponse response) throws AuthenticationException {
        redisUtil = (RedisUtil) SpringContextUtil.getBean("redisUtil");

        // 从输入流中获取到登录的信息
        String body;
        try {
            body = StreamUtils.copyToString(request.getInputStream(), StandardCharsets.UTF_8);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
            String username = null, password = null,uuid,captcha;
            if(StringUtils.hasText(body)) {
                JSONObject jsonObj = JSONObject.parseObject(body);
                username = jsonObj.getString("username");
                password = jsonObj.getString("password");
                uuid = jsonObj.getString("uuid");
                captcha = jsonObj.getString("captcha");
                String captcha2 = redisUtil.getJedis().get(uuid);
                if(!captcha.equals(captcha2)){
                    throw new InvalidCookieException("验证码验证失败");
                }
            }
            return authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(username, password, new ArrayList<>())
            );
    }

    // 成功验证后调用的方法
    // 如果验证成功，就生成token并返回
    @Override
    protected void successfulAuthentication(HttpServletRequest request,
                                            HttpServletResponse response,
                                            FilterChain chain,
                                            Authentication authResult) throws IOException, ServletException {

        // 查看源代码会发现调用getPrincipal()方法会返回一个实现了`UserDetails`接口的对象
        // 所以就是JwtUser啦
        JwtUser jwtUser = (JwtUser) authResult.getPrincipal();
        System.out.println("jwtUser:" + jwtUser.toString());
        String token = JwtTokenUtil.createToken(jwtUser.getUsername(), false);
        // 返回创建成功的token
        // 但是这里创建的token只是单纯的token
        // 按照jwt的规定，最后请求的格式应该是 `Bearer token`
        response.setHeader("token", JwtTokenUtil.TOKEN_PREFIX + token);
    }

    // 这是验证失败时候调用的方法
    @Override
    protected void unsuccessfulAuthentication(HttpServletRequest request, HttpServletResponse response, AuthenticationException failed) throws IOException, ServletException {
        response.getWriter().write("authentication failed, reason: " + failed.getMessage());
    }

}
