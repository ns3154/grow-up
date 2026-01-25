package com.example.mvc.config.bean;

import jakarta.annotation.Resource;
import org.springframework.stereotype.Component;

/**
 * <p>
 *
 * </p>
 *
 * @author Ns
 * @version 1.0
 * @date 2023/02/07 16:43
 **/
@Component
public class TestB {

    @Resource
    private TestA testA;

    static {
        System.out.println(".... testb");
    }
}
