package com.example.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.context.EnvironmentAware;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Component;


/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/07 14:59
 **/
@Configuration
public class MyHolderAware implements BeanNameAware , BeanClassLoaderAware,
        BeanFactoryAware, EnvironmentAware, InitializingBean, SmartInitializingSingleton, DisposableBean {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String desc;

    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    public MyHolderAware() {
        this.desc = "desc 1";
        logger.info("***** MyHolderAware 初始化***");
    }

    @Override
    public void setBeanName(String name) {
        this.beanName = name;
    }

    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.info("*** MyHolderAware#afterPropertiesSet()**** ");
    }

    @Override
    public void afterSingletonsInstantiated() {
        logger.info("*** MyHolderAware#afterSingletonsInstantiated()**** ");
    }

    @Override
    public void destroy() throws Exception {
        logger.info("*** MyHolderAware#destroy()**** ");
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    @Override
    public String toString() {
        return "MyHolderAware{" + "desc='" + desc + '\'' + '}';
    }
}
