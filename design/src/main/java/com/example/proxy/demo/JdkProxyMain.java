package com.example.proxy.demo;

import com.example.proxy.demo.model.UserPO;
import com.example.proxy.demo.proxy.JdkProxy;
import com.example.proxy.demo.service.UserService;
import com.example.proxy.demo.service.impl.UserServiceImpl;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:28
 **/
public class JdkProxyMain {

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();

        JdkProxy jdkProxy = new JdkProxy(userService);
        UserService userServiceProxy = (UserService) jdkProxy.getProxy();
        userServiceProxy.addUser(new UserPO.Builder().id(1L).name("杨").sex(1).build());
        userServiceProxy.getUserById(1L);


    }
}
