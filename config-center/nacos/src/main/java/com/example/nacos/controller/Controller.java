package com.example.nacos.controller;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/")
@ConfigurationProperties
public class Controller {

    @NacosValue(value = "${test}")
    private String test;


    @GetMapping("index")
    public Object index() {
        return test;
    }
}
