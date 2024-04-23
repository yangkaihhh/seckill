package com.kill.controller;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/test")
public class kill {

    @RequestMapping("/test")
    public String test(){
        return "test";
    }

}
