package com.example.ioc.cyclicdependency.filed;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.DefaultApplicationArguments;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <pre>
 *      字段循环依赖
 *      Spring 只解决 scope 为 singleton 的循环依赖，对于scope 为 prototype 的 bean Spring 无法解决
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/09 15:02
 **/
@Component
//@Scope(scopeName = ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class A implements SmartInitializingSingleton , CommandLineRunner, ApplicationRunner {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private B b;

    private String name;

    public A() {
        this.name = "A";
        System.out.println("实例化 A .....");
    }

    @Override
    public void run(String... args) throws Exception {
        logger.error("*** CommandLineRunner.A#run, args:{}***", args);
    }

    @Override
    public void run(ApplicationArguments args) throws Exception {
        logger.error("*** ApplicationRunner.A#run, args:{}***", args.getNonOptionArgs());
    }

    @Override
    public void afterSingletonsInstantiated() {
        System.out.println("A 完成");
    }
}
