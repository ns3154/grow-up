package com.example.lifecycle;

import com.example.model.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/07 15:33
 **/
@Component
public class MyInstantiationAwareBeanPostProcessor implements InstantiationAwareBeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * bean 实例化之前操作
     *
     * @author 杨帮东
     * @param beanClass class
     * @param beanName beanName
     * @since 1.0
     * @date 2020/4/7 16:37
     * @return java.lang.Object
     *          如果返回非空,代表自己实例化 不再执行
     *          {@link #postProcessAfterInstantiation}
     *          {@link #postProcessProperties}
     *          后续方法
     * @throws
     */
    @Override
    public Object postProcessBeforeInstantiation(Class<?> beanClass, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            logger.info("***MyInstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation****");
            // 自己实例化
            return User.newBuilder().withAge(22222).withUserName("yang").withId(1L).build();
        }
        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            logger.info("***MyInstantiationAwareBeanPostProcessor#postProcessAfterInstantiation****");
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            logger.info("***MyInstantiationAwareBeanPostProcessor#postProcessProperties****");
            final MutablePropertyValues propertyValues;

            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            propertyValues.add("desc", "InstantiationAwareBeanPostProcessor#postProcessPropertiesv1");
            return propertyValues;
        }

        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName)) {
            logger.info("***MyInstantiationAwareBeanPostProcessor#postProcessBeforeInitialization****");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if ("user".equals(beanName) && User.class.equals(bean.getClass())) {
            logger.info("***MyInstantiationAwareBeanPostProcessor#postProcessAfterInitialization****");
            User user = (User) bean;
            user.setDesc("BeanPostProcessor#postProcessAfterInitializationV2");
            return user;
        }
        return bean;
    }
}
