package com.whc.mix_api.controller;

import com.whc.mix_api.api.ApiResult;
import com.whc.mix_api.service.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;

/**
 * @author whc
 * @date 2020/9/18
 * @description 演职人员controller
 */
@RestController
@RequestMapping("/person")
public class PersonController {

    @Resource
    private PersonService personService;

    @GetMapping("/{id}")
    public ApiResult getPerson(@PathVariable("id") Integer id){
        return personService.getPerson(id);
    }
}
