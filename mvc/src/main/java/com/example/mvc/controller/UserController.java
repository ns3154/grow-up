package com.example.mvc.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 16:03
 **/
@RestController
@RequestMapping("user")
public class UserController {

    @GetMapping("/")
    public String index(String s) {
        return s;
    }
}
