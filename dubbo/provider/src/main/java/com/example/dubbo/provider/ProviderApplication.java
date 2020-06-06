package com.example.dubbo.provider;

import org.apache.dubbo.config.spring.ServiceBean;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/23 11:07
 **/
@SpringBootApplication
public class ProviderApplication {

    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(ProviderApplication.class, args);
        ServiceBean bean = run.getBean("ServiceBean:com.example.common.api.AsyncDubboServiceTestApi",
                ServiceBean.class);
        System.out.println(bean.getBeanName());
    }
}
