package com.example.lifecycle;

import com.example.model.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.MutablePropertyValues;
import org.springframework.beans.PropertyValues;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.InstantiationAwareBeanPostProcessor;
import org.springframework.beans.factory.config.SmartInstantiationAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

import java.beans.PropertyDescriptor;
import java.lang.reflect.Constructor;
import java.util.HashSet;
import java.util.Set;

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

    private static Set<String> myBeanNames = new HashSet<>();

    static {
        myBeanNames.add("a");
        myBeanNames.add("b");
        myBeanNames.add("c");
        myBeanNames.add("user");
    }
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

            // 自己实例化,如果自己实例化 postProcessProperties 方法不会在执行
//            return User.newBuilder().withAge(22222).withUserName("yang").withId(1L).build();
        }

        if (myBeanNames.contains(beanName)) {
            logger.info("***beanName:{} , MyInstantiationAwareBeanPostProcessor#postProcessBeforeInstantiation****",
                    beanName);
        }

        return null;
    }

    @Override
    public boolean postProcessAfterInstantiation(Object bean, String beanName) throws BeansException {
        if (myBeanNames.contains(beanName)) {
            logger.info("***beanName:{} , MyInstantiationAwareBeanPostProcessor#postProcessAfterInstantiation****",
                    beanName);
        }
        return true;
    }

    @Override
    public PropertyValues postProcessProperties(PropertyValues pvs, Object bean, String beanName) throws BeansException {
        if (myBeanNames.contains(beanName)) {
            logger.info("***beanName:{} ,MyInstantiationAwareBeanPostProcessor#postProcessProperties****", beanName);
        }

        if ("user".equals(beanName)) {
            final MutablePropertyValues propertyValues;
            if (pvs instanceof MutablePropertyValues) {
                propertyValues = (MutablePropertyValues) pvs;
            } else {
                propertyValues = new MutablePropertyValues();
            }
            logger.info("***InstantiationAwareBeanPostProcessor#postProcessPropertie#desc:{}s***",
                    propertyValues.get("desc"));
            propertyValues.add("desc", "InstantiationAwareBeanPostProcessor#postProcessPropertiesv2");
            return propertyValues;
        }

        return null;
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (myBeanNames.contains(beanName)) {
            logger.info("***beanName:{} ,MyInstantiationAwareBeanPostProcessor#postProcessBeforeInitialization****",
                    beanName);
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (myBeanNames.contains(beanName)) {
            logger.info("***beanName:{} ,MyInstantiationAwareBeanPostProcessor#postProcessAfterInitialization****",
                    beanName);

        }

        if ("user".equals(beanName) && User.class.equals(bean.getClass())) {
            User user = (User) bean;
            user.setDesc("BeanPostProcessor#postProcessAfterInitializationV3");
            return user;
        }
        return bean;
    }

}
