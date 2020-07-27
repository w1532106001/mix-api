package com.whc.mix_api.service.impl;

import com.whc.mix_api.auth.model.ImageCode;
import com.whc.mix_api.service.IUserService;
import com.whc.mix_api.service.ValidateCodeGenerator;
import com.whc.mix_api.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whc
 * @date 2020/5/29
 * @description 用户接口
 */
@Service
@Slf4j
public class UserServiceImpl implements IUserService {

    @Resource
    private RedisUtil redisUtil;

    private final ValidateCodeGenerator validateCodeGenerator;

    public UserServiceImpl(@Qualifier("imageCodeGenerator") ValidateCodeGenerator validateCodeGenerator) {
        this.validateCodeGenerator = validateCodeGenerator;
    }

    @Override
    public void getLoginCaptcha(String loginName,HttpServletRequest request, HttpServletResponse response){
        ImageCode validateCode = (ImageCode) validateCodeGenerator.generate(request);
        try {
            ImageIO.write(validateCode.getImage(), "JPEG", response.getOutputStream());
            redisUtil.getJedis().hset("LoginImageCode", loginName,validateCode.getCode());
        } catch (IOException e) {
            log.error("登录验证码生成失败");
        }
    }

    @Override
    public void send(String mobile, String code) {
        Map<String,String> map = new HashMap(20);
        map.put(mobile,"1234");
        redisUtil.getJedis().set("SMSLoginCode", String.valueOf(map));
    }


    /**
     * 获取用户真实IP地址，不使用request.getRemoteAddr();的原因是有可能用户使用了代理软件方式避免真实IP地址。
     * 可是，如果通过了多级反向代理的话，X-Forwarded-For的值并不止一个，而是一串IP值，究竟哪个才是真正的用户端的真实IP呢？
     * 答案是取X-Forwarded-For中第一个非unknown的有效IP字符串
     * @param request
     * @return
     */
    private static String getIpAddress(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_CLIENT_IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("HTTP_X_FORWARDED_FOR");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
            if("127.0.0.1".equals(ip)||"0:0:0:0:0:0:0:1".equals(ip)){
                //根据网卡取本机配置的IP
                InetAddress inet=null;
                try {
                    inet = InetAddress.getLocalHost();
                } catch (UnknownHostException e) {
                    e.printStackTrace();
                }
                ip= inet != null ? inet.getHostAddress() : null;
            }
        }
        return ip;
    }

}
