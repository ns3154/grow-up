package com.example.dubbo.consumer.controller;

import com.example.common.api.DubboTestServiceApi;
import com.example.common.model.ModelMessage;
import org.apache.dubbo.config.annotation.Reference;
import org.apache.dubbo.rpc.service.EchoService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.beans.factory.FactoryBean;
import org.springframework.context.ApplicationContext;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * <pre>
 *     DUBBO 回升测试
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/28 15:52
 **/
@RestController
@RequestMapping("echo")
public class EchoController {

    @Resource
    private ApplicationContext applicationContext;

    @GetMapping("test")
    public ModelMessage<String> echo() {
        EchoService  echoService = applicationContext.getBean("dubboTestServiceApi", EchoService.class);
        Object o =  echoService.$echo("ok");
        return new ModelMessage<>(o.toString());
    }


}
