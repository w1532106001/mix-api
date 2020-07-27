package com.whc.mix_api.auth;

import com.whc.mix_api.config.model.JwtUser;
import com.whc.mix_api.mapper.UserMapper;
import com.whc.mix_api.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Author: WHC
 * @Date: 2019/10/12 11:10
 * @description
 */
@Component
public class MyUserDetailsServiceImpl implements UserDetailsService {

    @Resource
    private UserMapper userMapper;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {
        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        String loginType = request.getParameter("loginType");
        User user = null;
        if(loginType!=null){
            switch (loginType){
                case "1":
                    user = userMapper.selectByUserName(s);
                    break;
                case "2":
                    user = userMapper.selectByMobile(s);
                    break;
                default: user = userMapper.selectByUserName(s);
            }
        }else{
            user = userMapper.selectByUserName(s);
        }


        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new JwtUser(user);
    }
}
