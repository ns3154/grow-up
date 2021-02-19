package com.example.ioc;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/09 15:28
 **/
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
@Component
public class UserBean {

    @Value("${value:UserBean}")
    private String value;

    public UserBean() {
        System.out.println("instantiation [ɪnˌstænʃiˈeɪʃən] start.....");
    }

    public void out() {
        System.out.println(value);
    }
}
