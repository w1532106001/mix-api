package com.whc.mix_api.auth;

import com.alibaba.fastjson.JSONObject;
import com.whc.mix_api.model.User;
import com.whc.mix_api.utils.RedisUtil;
import com.whc.mix_api.utils.TokenUtil;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.annotation.Resource;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */
@Component
public class LoginAuthorizationFilter extends OncePerRequestFilter {


    @Resource
    private UserDetailsService myUserDetailsServiceImpl;

    @Resource
    private RedisUtil redisUtil;

    @Override
    protected void doFilterInternal(HttpServletRequest request,
                                    HttpServletResponse response,
                                    FilterChain chain) throws IOException, ServletException {

        String tokenHeader = request.getHeader(TokenUtil.TOKEN_HEADER);
        // 如果请求头中没有Authorization信息则直接放行了
        if (tokenHeader != null && tokenHeader.startsWith(TokenUtil.TOKEN_PREFIX)) {
            String result = redisUtil.getJedis().hget("LoginToken", tokenHeader);
            User user = JSONObject.parseObject(result, User.class);
            if (user.getUsername() != null && SecurityContextHolder.getContext().getAuthentication() == null) {
                //根据用户名获取用户对象
                UserDetails userDetails = myUserDetailsServiceImpl.loadUserByUsername(user.getUsername());
                if (userDetails != null) {
                    UsernamePasswordAuthenticationToken authentication =
                            new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                    authentication.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                    //设置为已登录
                    SecurityContextHolder.getContext().setAuthentication(authentication);
                }
            }
        }
        chain.doFilter(request, response);
    }

}

