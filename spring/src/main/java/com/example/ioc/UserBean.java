package com.example.ioc;

import org.springframework.beans.factory.annotation.Value;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/09 15:28
 **/
public class UserBean {

    @Value("${value}")
    private String value;

    public UserBean() {
        System.out.println("instantiation [ɪnˌstænʃiˈeɪʃən] start.....");
    }

    public void out() {
        System.out.println(value);
    }
}
