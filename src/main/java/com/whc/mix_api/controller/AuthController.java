package com.whc.mix_api.controller;

import com.whc.mix_api.mapper.UserMapper;
import com.whc.mix_api.model.User;
import com.whc.mix_api.service.IUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author whc
 * @date 2020/5/29
 * @description
 */

@RestController
@RequestMapping("/auth")
public class AuthController {

    // 为了减少篇幅就不写service接口了
    @Resource
    private UserMapper userMapper;
    @Resource
    private IUserService userService;


    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @PostMapping("/register")
    public int registerUser(String username,String password){
        User user = new User();
        user.setUsername(username);
        // 记得注册的时候把密码加密一下
        user.setPassword(bCryptPasswordEncoder.encode(password));
        user.setRole("normal");
        return userMapper.insert(user);
    }

    @PostMapping("/captcha")
    public void getCaptcha(String loginName, HttpServletRequest request, HttpServletResponse response) throws Exception {
        throw new Exception("123");
//       userService.getLoginCaptcha(loginName, request, response);
    }

    @PostMapping("/smsLoginCode")
    public void sendSMSVerifyCodeByLogin(String mobile,String code) throws Exception {
        userService.send(mobile,code);
    }
}

