package com.example;

import com.example.aware.MyHolderAware;
import com.example.config.MyPropertry;
import com.example.model.bean.User;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import javax.annotation.Resource;

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

    private static Logger logger = LoggerFactory.getLogger(Spring.class);


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Spring.class, args);
        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        User user = beanFactory.getBean("user", User.class);
        MyPropertry propertry = beanFactory.getBean("myPropertry", MyPropertry.class);
        logger.error(user.toString());
        logger.error(propertry.toString());
    }
}
