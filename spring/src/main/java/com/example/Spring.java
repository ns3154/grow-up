package com.example;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 16:00
 **/
@SpringBootApplication
@MapperScan(basePackages = "com.example.mybatis.dao")
public class Spring {

    public static void main(String[] args) {
        SpringApplication.run(Spring.class, args);
    }
}
