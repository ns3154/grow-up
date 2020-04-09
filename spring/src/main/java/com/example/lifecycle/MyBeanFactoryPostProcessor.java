package com.example.lifecycle;

import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/09 18:35
 **/
@Component
public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor {

    /**
     * 实例化之前
     * @param beanFactory
     * @throws BeansException
     */
    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        BeanDefinition definition = beanFactory.getBeanDefinition("user");
        MutablePropertyValues propertyValues = definition.getPropertyValues();
        propertyValues.add("desc", "BeanFactoryPostProcessor#V1");
    }
}
