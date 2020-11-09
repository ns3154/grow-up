package com.example.config;

import com.example.annotation.MyAutowired;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.annotation.AutowiredAnnotationBeanPostProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/11/09 23:30
 **/
//@Component
public class BeanPostProcessorConfiguration {

    @Bean("myAutowiredAnnotationBeanPostProcessor")
    public AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor() {
        AutowiredAnnotationBeanPostProcessor autowiredAnnotationBeanPostProcessor =
                new AutowiredAnnotationBeanPostProcessor();
        autowiredAnnotationBeanPostProcessor.setAutowiredAnnotationType(MyAutowired.class);
        return autowiredAnnotationBeanPostProcessor;
    }
}
