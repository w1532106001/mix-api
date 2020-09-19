package com.whc.mix_api.auth;

/**
 * @author whc
 * @date 2020/5/31 1:26
 */

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.SecurityConfigurerAdapter;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.DefaultSecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

@Component
public class LoginAuthenticationSecurityConfig extends SecurityConfigurerAdapter<DefaultSecurityFilterChain, HttpSecurity> {

    @Autowired
    private AuthenticationSuccessHandler myAuthenticationSuccessHandler;

    @Autowired
    private AuthenticationFailureHandler myAuthenticationFailureHandler;

    @Resource
    private UserDetailsService myUserDetailsServiceImpl;
    @Resource
    private LoginAuthenticationProvider loginAuthenticationProvider;


    @Override
    public void configure(HttpSecurity http) throws Exception {

        LoginAuthenticationFilter loginAuthenticationFilter = new LoginAuthenticationFilter();
        loginAuthenticationFilter.setAuthenticationManager(http.getSharedObject(AuthenticationManager.class));
        loginAuthenticationFilter.setAuthenticationSuccessHandler(myAuthenticationSuccessHandler);
        loginAuthenticationFilter.setAuthenticationFailureHandler(myAuthenticationFailureHandler);


        // 将短信验证码校验器注册到 HttpSecurity， 并将短信验证码过滤器添加在 UsernamePasswordAuthenticationFilter 之前
        http.authenticationProvider(loginAuthenticationProvider).addFilterAfter(loginAuthenticationFilter, UsernamePasswordAuthenticationFilter.class);

    }
}
