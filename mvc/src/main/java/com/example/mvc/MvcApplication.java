package com.example.mvc;

import com.example.mvc.config.bean.TestA;
import com.example.mvc.controller.Controller;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class MvcApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext context = SpringApplication.run(MvcApplication.class, args);
        ConfigurableListableBeanFactory factory = context.getBeanFactory();
        String[] as = factory.getDependentBeans("testA");

        String[] bs = factory.getDependentBeans("testB");
        factory.getDependenciesForBean("testB");
        System.out.println(as);

    }

}
