package com.example.enable;

import org.springframework.context.annotation.Bean;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/24 11:10
 **/
public class BeanDefinitionRegistrarTest {


    @Bean
    public String beanDefinitionRegistrarTestStr() {
        return "beanDefinitionRegistrarTestStr";
    }
}
