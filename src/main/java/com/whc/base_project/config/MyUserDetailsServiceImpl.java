package com.whc.base_project.config;

import com.whc.base_project.config.model.JwtUser;
import com.whc.base_project.mapper.UserMapper;
import com.whc.base_project.model.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

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
        User user = userMapper.selectByUserName(s);
        if(user == null){
            throw new UsernameNotFoundException("用户不存在");
        }
        return new JwtUser(user);
    }
}
