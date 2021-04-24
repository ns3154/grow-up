package com.example.mybatisplus.controller;

import com.example.mybatisplus.dao.LclOrderMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/04/24 22:07
 **/
@RequestMapping("/mybatis/plus")
@RestController
public class Controller {

    @Resource
    private LclOrderMapper lclOrderMapper;

    @GetMapping("select")
    public Object select() {
        return lclOrderMapper.selectById(1);
    }

}
