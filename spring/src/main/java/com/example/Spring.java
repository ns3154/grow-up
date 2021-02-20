package com.example;

import com.example.annotation.MyAutowired;
import com.example.config.MyPropertry;
import com.example.enable.MyEnable;
import com.example.ioc.cyclicdependency.filed.A;
import com.example.model.bean.User;
import org.mybatis.spring.annotation.MapperScan;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.transaction.TransactionAutoConfiguration;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.EnableAspectJAutoProxy;
import org.springframework.core.Ordered;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.transaction.annotation.ProxyTransactionManagementConfiguration;

import javax.sql.DataSource;

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
@MyEnable
@EnableAspectJAutoProxy(exposeProxy = true)
public class Spring {

    private static final Logger logger = LoggerFactory.getLogger(Spring.class);

    @Autowired
    private User user;

    @MyAutowired
    private User user1;


    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(Spring.class, args);

        ConfigurableListableBeanFactory beanFactory = run.getBeanFactory();
        Spring bean = beanFactory.getBean(Spring.class);
        System.out.println(bean.user == bean.user1);
        User user = beanFactory.getBean("user", User.class);
        MyPropertry propertry = beanFactory.getBean("myPropertry", MyPropertry.class);
        A a = beanFactory.getBean("a", A.class);
        logger.error(user.toString());
        logger.error(propertry.toString());
        logger.error(a.toString());

        // @MyEnable
        String enableTestImport = beanFactory.getBean("enableTestImport", String.class);
        String enableImportSelectorTest = beanFactory.getBean("enableImportSelectorTest", String.class);
        String beanDefinitionRegistrarTestStr = beanFactory.getBean("beanDefinitionRegistrarTestStr", String.class);
        logger.error(enableTestImport);
        logger.error(enableImportSelectorTest);
        logger.error(beanDefinitionRegistrarTestStr);
    }
}
