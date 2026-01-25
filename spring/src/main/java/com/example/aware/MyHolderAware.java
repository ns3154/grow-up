package com.example.aware;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.*;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.weaving.LoadTimeWeaverAware;
import org.springframework.core.env.Environment;
import org.springframework.core.io.ResourceLoader;
import org.springframework.instrument.classloading.LoadTimeWeaver;
import org.springframework.jmx.export.notification.NotificationPublisher;
import org.springframework.jmx.export.notification.NotificationPublisherAware;
import org.springframework.web.context.ServletConfigAware;
import org.springframework.web.context.ServletContextAware;

import jakarta.servlet.ServletConfig;
import jakarta.servlet.ServletContext;


/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/07 14:59
 **/
@Configuration
public class MyHolderAware implements BeanFactoryPostProcessor, BeanPostProcessor, DestructionAwareBeanPostProcessor,
        BeanNameAware , BeanClassLoaderAware,InitializingBean, InstantiationAwareBeanPostProcessor,
        BeanFactoryAware, EnvironmentAware, SmartInitializingSingleton, DisposableBean, LoadTimeWeaverAware,
        ResourceLoaderAware, ServletConfigAware, ServletContextAware, MessageSourceAware,
        ApplicationEventPublisherAware, NotificationPublisherAware /**,BootstrapContextAware*/

{

    private Logger logger = LoggerFactory.getLogger(getClass());

    private String desc;

    private String beanName;

    private ClassLoader classLoader;

    private BeanFactory beanFactory;

    private Environment environment;

    public MyHolderAware() {
        this.desc = "desc 1";
        logger.error("***** MyHolderAware 初始化***");
    }

    @Override
    public void setBeanName(String name) {
        logger.error("MyHolderAware.setBeanName");
        this.beanName = name;
    }

    /**
     * 加载Spring Bean的类加载器
     * @param classLoader
     */
    @Override
    public void setBeanClassLoader(ClassLoader classLoader) {
        logger.error("MyHolderAware.setBeanClassLoader");
        this.classLoader = classLoader;
    }

    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        logger.error("MyHolderAware.setBeanFactory");
        this.beanFactory = beanFactory;
    }

    @Override
    public void setEnvironment(Environment environment) {
        logger.error("MyHolderAware.setEnvironment");
        this.environment = environment;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.error("*** MyHolderAware#afterPropertiesSet()**** ");
    }

    @Override
    public void afterSingletonsInstantiated() {
        logger.error("*** MyHolderAware#afterSingletonsInstantiated()#desc:{}**** ", desc);
    }

    @Override
    public void destroy() throws Exception {
        logger.error("*** MyHolderAware#destroy()**** ");
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

    /**
     * 加载Spring Bean时织入第三方模块，如AspectJ
     * @param loadTimeWeaver
     */
    @Override
    public void setLoadTimeWeaver(LoadTimeWeaver loadTimeWeaver) {
        logger.error("*** MyHolderAware#setLoadTimeWeaver***");
    }

//    /**
//     * 资源适配器BootstrapContext，如JCA,CCI
//     * @param bootstrapContext
//     */
//    @Override
//    public void setBootstrapContext(javax.resource.spi.BootstrapContext bootstrapContext) {
//        logger.error("*** MyHolderAware#setBootstrapContext***");
//    }

    /**
     * 底层访问资源的加载器
     * @param resourceLoader
     */
    @Override
    public void setResourceLoader(ResourceLoader resourceLoader) {
        logger.error("*** MyHolderAware#setResourceLoader***");
    }

    @Override
    public void setServletConfig(ServletConfig servletConfig) {
        logger.error("*** MyHolderAware#setServletConfig***");
    }

    @Override
    public void setServletContext(ServletContext servletContext) {
        logger.error("*** MyHolderAware#setServletContext***");
    }

    @Override
    public void setApplicationEventPublisher(ApplicationEventPublisher applicationEventPublisher) {
        logger.error("*** MyHolderAware#setApplicationEventPublisher***");
    }

    @Override
    public void setMessageSource(MessageSource messageSource) {
        logger.error("*** MyHolderAware#setMessageSource***");
    }

    @Override
    public void setNotificationPublisher(NotificationPublisher notificationPublisher) {
        logger.error("*** MyHolderAware#setNotificationPublisher***");
    }


    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
        logger.error("*** MyHolderAware#postProcessBeanFactory***");
    }

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        if (beanName.equals("user")) {
            logger.error("*** MyHolderAware#postProcessBeforeDestruction***");
        }
    }

    @Override
    public Object postProcessBeforeInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("user")) {
            logger.error("*** MyHolderAware#postProcessBeforeInitialization***");
        }
        return bean;
    }

    @Override
    public Object postProcessAfterInitialization(Object bean, String beanName) throws BeansException {
        if (beanName.equals("user")) {
            logger.error("*** MyHolderAware#postProcessAfterInitialization***");
        }
        return bean;
    }
}
