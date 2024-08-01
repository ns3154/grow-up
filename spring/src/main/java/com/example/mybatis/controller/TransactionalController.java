package com.example.mybatis.controller;

import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;

@RequestMapping("transaction")
public class TransactionalController {

    @Resource
    private TestMapper testMapper;


    @GetMapping("nest")
    public void nest() {

    }
}
