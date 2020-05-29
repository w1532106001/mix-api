package com.whc.base_project.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @Author: WHC
 * @Date: 2019/10/11 11:52
 * @description
 */
@Controller(value = "/index")
public class IndexController {
    @GetMapping("")
    public String indexHtml(){
        return "index/index";
    }


}
