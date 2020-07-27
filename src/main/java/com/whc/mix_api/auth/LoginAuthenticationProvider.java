package com.whc.mix_api.auth;

import com.alibaba.fastjson.JSONObject;
import com.whc.mix_api.auth.model.ValidateCode;
import com.whc.mix_api.utils.RedisUtil;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
@Component
public class LoginAuthenticationProvider implements AuthenticationProvider {

    @Resource
    private UserDetailsService myUserDetailsServiceImpl;

    @Resource
    private RedisUtil redisUtil;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        LoginAuthenticationToken authenticationToken = (LoginAuthenticationToken) authentication;
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();

        String loginName = (String) authenticationToken.getPrincipal();
        UserDetails userDetails = myUserDetailsServiceImpl.loadUserByUsername(loginName);

        String loginType = request.getParameter("loginType");
        switch (Integer.parseInt(loginType)){
            case 1:
                checkImageCode();
                if(bCryptPasswordEncoder.matches(bCryptPasswordEncoder.encode(request.getParameter("password")),userDetails.getPassword())){
                    throw new BadCredentialsException("密码错误");
                }
                break;
            case 2:
                checkSmsCode();
            default:break;
        }

        // 此时鉴权成功后，应当重新 new 一个拥有鉴权的 authenticationResult 返回
        LoginAuthenticationToken authenticationResult = new LoginAuthenticationToken(userDetails, userDetails.getAuthorities());

        authenticationResult.setDetails(authenticationToken.getDetails());

        return authenticationResult;
    }

    private void checkSmsCode() {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("smsCode");
        String mobile = request.getParameter("loginName");
        String smsCode = redisUtil.getJedis().hget("smsCode",mobile);

        if(smsCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }
        ValidateCode validateCode = JSONObject.parseObject(smsCode,ValidateCode.class);

        if(!StringUtils.equals(validateCode.getCode(),inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }

    private void checkImageCode(){
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String inputCode = request.getParameter("imageCode");
        String loginName = request.getParameter("loginName");
        String imageCode = redisUtil.getJedis().hget("LoginImageCode",loginName);

        if(imageCode == null) {
            throw new BadCredentialsException("未检测到申请验证码");
        }
//        ImageCode validateCode = JSONObject.parseObject(imageCode, ImageCode.class);

        if(!StringUtils.equals(imageCode,inputCode)) {
            throw new BadCredentialsException("验证码错误");
        }
    }

//    private void check

    @Override
    public boolean supports(Class<?> authentication) {
        // 判断 authentication 是不是 SmsCodeAuthenticationToken 的子类或子接口
        return LoginAuthenticationToken.class.isAssignableFrom(authentication);
    }

}