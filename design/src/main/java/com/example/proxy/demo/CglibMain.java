package com.example.proxy.demo;

import com.alibaba.fastjson.JSON;
import com.example.proxy.demo.model.UserPO;
import com.example.proxy.demo.proxy.CgLibProxy;
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
 * @date 2020/03/21 14:51
 **/
public class CglibMain {

    private static Logger logger = LoggerFactory.getLogger(CglibMain.class);

    public static void main(String[] args) throws InterruptedException {
        CgLibProxy cgLibProxy = new CgLibProxy();
        UserServiceImpl instance = (UserServiceImpl) cgLibProxy.getInstance(new UserServiceImpl(), UserService.class);
        boolean b = instance.addUser(new UserPO.Builder().id(1L).name("杨").sex(2).build());
        UserPO user = instance.getUserById(1L);
        boolean b1 = instance.delUser(1L);
        logger.error("** main 返回参数:{},{}, {}", b, JSON.toJSONString(user), b1);
        Thread.sleep(1000000000000L);

    }
}
