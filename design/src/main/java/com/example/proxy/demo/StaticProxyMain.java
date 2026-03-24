package com.example.proxy.demo;

import com.alibaba.fastjson.JSON;
import com.example.proxy.demo.easy.UserServiceProxy;
import com.example.proxy.demo.model.UserPO;
import com.example.proxy.demo.service.UserService;
import com.example.proxy.demo.service.impl.UserServiceImpl;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:00
 **/
public class StaticProxyMain {

    private static Logger logger = LoggerFactory.getLogger(StaticProxyMain.class);

    public static void main(String[] args) {
        UserService userService = new UserServiceImpl();
        UserServiceProxy proxy = new UserServiceProxy(userService);
        UserPO user = new UserPO();
        user.setId(1L);
        user.setName("杨");
        user.setSex(1);
        boolean b = proxy.addUser(user);
        UserPO userById = proxy.getUserById(1L);

        logger.error("**** mian 执行,结果: {} , {}", b, JSON.toJSONString(userById));


    }
}
