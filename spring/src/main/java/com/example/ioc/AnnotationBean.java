package com.example.ioc;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Scope;
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
        applicationContext.registerBean("userBean", UserBean.class);

        AutowiredBean bean = applicationContext.getBean(AutowiredBean.class);
        for (int i = 0;i < 10;i++) {
            bean.out();
        }
        bean.out();

        applicationContext.close();
    }

    @Bean
    public AutowiredBean autowiredBean() {
        return new AutowiredBean();
    }


    public UserBean userBean() {
        return new UserBean();
    }
}
