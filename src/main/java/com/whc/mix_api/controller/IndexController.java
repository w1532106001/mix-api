package com.whc.mix_api.controller;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: WHC
 * @Date: 2019/10/11 11:52
 * @description
 */
@RestController
@RequestMapping("/index")
public class IndexController {
    @PostMapping("/index")
    public String indexHtml(){
        return "index/index";
    }



}
