package com.kill.controller;


import com.kill.utils.RabbitMqUtil;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@RequestMapping("/test")
public class kill {

    @Resource
    private RabbitMqUtil rabbitMqUtil;
    @RequestMapping("/test")
    public void test(){
        System.out.println("----------");
       rabbitMqUtil.sendMessage("hello----------");
    }



}
