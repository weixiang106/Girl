package com.example.demo.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Api(value = "设置中心")
public class SetingController {
    @GetMapping(value = "/hello")
    public String goList(){
        return "hello world";
    }
}
