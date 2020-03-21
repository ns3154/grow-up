package com.example.proxy.demo.easy;

import com.alibaba.fastjson.JSON;
import com.example.proxy.demo.model.UserPO;
import com.example.proxy.demo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:11
 **/
public class UserServiceProxy implements UserService {

    private Logger logger = LoggerFactory.getLogger(getClass());


    private UserService userService;

    public UserServiceProxy(UserService userService) {
        this.userService = userService;
    }

    @Override
    public boolean addUser(UserPO user) {
        logger.info("******* proxy 开始执行 **********");
        boolean b = userService.addUser(user);
        logger.info("******* proxy 执行结束,返回参数:{} **********", b);
        return b;
    }

    @Override
    public UserPO getUserById(Long id) {
        logger.info("******* proxy 开始执行 **********");
        UserPO user = userService.getUserById(id);
        logger.info("******* proxy 执行结束,返回参数:{} **********", JSON.toJSONString(user));
        return user;
    }
}
