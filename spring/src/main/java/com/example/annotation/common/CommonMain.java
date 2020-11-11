package com.example.annotation.common;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/11 14:32
 **/
public class CommonMain {

    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext("com.example.annotation.common");


        applicationContext.close();

    }
}
