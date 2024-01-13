package com.example.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController()
@RequestMapping("/setUp")

public class SetUpController {

    @GetMapping
    public Object setUp() {
        System.out.println(System.getProperty("my.property"));
        return "setUp";
    }
}
