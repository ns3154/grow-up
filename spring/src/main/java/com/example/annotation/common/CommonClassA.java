package com.example.annotation.common;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.DependsOn;
import org.springframework.stereotype.Component;

import jakarta.annotation.PostConstruct;
import jakarta.annotation.PreDestroy;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/11 14:31
 **/
@Component
public class CommonClassA {

    private CommonClassA() {
        System.out.println("CommonClassA instantiation..........");
    }

    @Value("${sss:322}")
    private Long id;

    @PostConstruct
    public void init() {
        System.out.println("CommonClassA initialization.....id=" + id);
    }

    @PreDestroy
    public void destroy() {
        System.out.println("CommonClassA destroy.....id=" + id);
    }
}
