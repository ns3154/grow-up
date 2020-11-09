package com.example.ioc;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.core.env.StandardEnvironment;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/09 15:06
 **/
public class AnnotationBean {


    public static void main(String[] args) {
        AnnotationConfigApplicationContext applicationContext = new AnnotationConfigApplicationContext();
        applicationContext.register(AnnotationBean.class);
        applicationContext.refresh();
        StandardEnvironment environment = new StandardEnvironment();

        UserBean bean = applicationContext.getBean(UserBean.class);
        System.out.println(bean.toString());
        bean.out();

        applicationContext.close();
    }


    @Bean
    public UserBean userBean() {
        return new UserBean();
    }
}
