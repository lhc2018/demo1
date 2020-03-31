package com.example.apitest.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @program: demo
 * @description:
 * @author: liying
 * @create: 2020-03-27 13:47:42
 **/
@RestController
public class TestController {

    @GetMapping(value = "/hello")
    public String hello(){
        return "hello";
    }
}
