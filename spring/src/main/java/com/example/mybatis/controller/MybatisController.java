package com.example.mybatis.controller;

import com.example.model.ModelMessge;
import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import com.example.mybatis.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/02 18:29
 **/
@RequestMapping("mybatis")
@RestController
public class MybatisController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public MybatisController() {
        //nothing
        logger.error("mybatisController 初始化....");
    }

    @Resource
    private TestMapper testMapper;

    @Resource
    private UserService userService;

    @GetMapping("select")
    public ModelMessge<Test> select(Long id) {
        Test test = userService.select(id);
        return new ModelMessge<Test>().ok(test);
    }

    @PostMapping("create")
    public ModelMessge<Test> create(Integer counts, Integer pNums) {
        Test test = userService.create(counts, pNums);
        return new ModelMessge<Test>().ok(test);
    }
}
