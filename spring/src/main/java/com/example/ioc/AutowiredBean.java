package com.example.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Lookup;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/10 14:54
 **/
@Component
public class AutowiredBean {

    @Lookup
    public UserBean userBean() {
        return null;
    };

    public void out() {
        userBean().out();
        System.out.println(userBean().hashCode());
    }
}
