package com.whc.mix_api.auth;

import org.apache.commons.lang3.StringUtils;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
public class LoginAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    /**
     * 登录方式
     */
    private final String LOGIN_TYPE = "loginType";
    /**
     * 登录账号
     */
    private final String LOGIN_NAME = "loginName";
    /**
     * 登录密码
     */
    private final String PASSWORD = "password";
    /**
     * 登录图片验证码
     */
    private final String IMAGE_CODE = "imageCode";
    /**
     * 登录短信校验码
     */
    private final String SMS_CODE = "smsCode";


    /**
     * 是否仅 POST 方式
     */
    private boolean postOnly = true;

    public LoginAuthenticationFilter() {
        // 短信登录的请求 post 方式的 /login
        super(new AntPathRequestMatcher("/user/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        if (postOnly && !"POST".equals(request.getMethod())) {
            throw new AuthenticationServiceException(
                    "Authentication method not supported: " + request.getMethod());
        }

        String loginType = request.getParameter(LOGIN_TYPE);
        if(StringUtils.isBlank(loginType)){
            throw new AuthenticationServiceException(
                    "参数异常");
        }
        String loginName = request.getParameter(LOGIN_NAME);
        switch (Integer.parseInt(loginType)){
            case 1:
                loginName = request.getParameter(LOGIN_NAME);
                break;
            case 2:
                loginName = request.getParameter(LOGIN_NAME);
                break;
            default:break;
        }

        loginName = loginName.trim();

        LoginAuthenticationToken authRequest = new LoginAuthenticationToken(loginName);

        // Allow subclasses to set the "details" property
        setDetails(request, authRequest);

        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, LoginAuthenticationToken authRequest) {
        authRequest.setDetails(authenticationDetailsSource.buildDetails(request));
    }


    public void setPostOnly(boolean postOnly) {
        this.postOnly = postOnly;
    }
}
