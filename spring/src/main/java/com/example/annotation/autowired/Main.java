package com.example.annotation.autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/10 15:57
 **/
public class Main {

    private static final Logger logger = LoggerFactory.getLogger(Main.class);

    public static void main(String[] args) {
        AnnotationConfigApplicationContext an = new AnnotationConfigApplicationContext("com.example.annotation.autowired");
        an.register(Main.class);
        ClassA classA = an.getBean(ClassA.class);
        logger.error("------------------- LOOKUP------------------------");
        for (int i = 0;i < 10;i++) {
            classA.lookup();
        }
        logger.error("------------------- LOOKUP------------------------");
        classA.out();
        an.close();
    }
}
