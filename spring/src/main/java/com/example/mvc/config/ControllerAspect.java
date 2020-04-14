package com.example.mvc.config;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/14 19:26
 **/
@Aspect
@Component
public class ControllerAspect {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Value("${test.user.name}")
    private String name;

    @Pointcut(value = "execution(public * com.example.mvc.controller..*.*(..))")
    private void controller(){};

    @Around(value = "controller()")
    public Object handler(ProceedingJoinPoint joinPoint) {
        logger.info("***** 切面:代码执行前 ******");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.info("***** 切面:代码执行后 ******");
        return proceed;
    }
}
