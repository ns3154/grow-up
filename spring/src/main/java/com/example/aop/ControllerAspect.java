package com.example.aop;

import com.example.utils.TrackUtils;
import com.google.common.util.concurrent.RateLimiter;
import javassist.*;
import javassist.bytecode.CodeAttribute;
import javassist.bytecode.LocalVariableAttribute;
import javassist.bytecode.MethodInfo;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import jakarta.annotation.Resource;
import java.time.Duration;

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

    @Pointcut(value = "execution(public * com.example.mybatis.service.impl.*.*(..))")
    private void service(){};

    private RateLimiter rateLimiter = RateLimiter.create(11);

    @Around(value = "controller()")
    public Object controllerHandler(ProceedingJoinPoint joinPoint) {
        logger.error("***** controller 切面:代码执行前 ******");
        Object proceed = null;
        rateLimiter.tryAcquire();
        try {
            MethodSignature signature = (MethodSignature) joinPoint.getSignature();
            Class<?> clazz = joinPoint.getTarget().getClass();
            String[] fieldsName = getFieldsName(clazz, clazz.getName(), signature.getMethod().getName());
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.error("***** controller 切面:代码执行后 ******");
        TrackUtils.printTrack("controller aop ....");
        return proceed;
    }

//    @Around(value = "service()")
    public Object serviceHandler(ProceedingJoinPoint joinPoint) {
        logger.error("***** service 切面:代码执行前 ******");
        Object proceed = null;
        try {
            proceed = joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
        }
        logger.error("***** service 切面:代码执行后 ******");
        TrackUtils.printTrack("service aop ....");
        return proceed;
    }

    /**
     * javassist 使用不当内存泄漏bug
     * @author 杨帮东
     * @param cls
     * @param clazzName
     * @param methodName
     * @since 1.0
     * @date 2021/1/7 10:46
     * @return java.lang.String[]
     * @throws
     */
    private String[] getFieldsName(Class cls, String clazzName, String methodName) throws NotFoundException {

        ClassPool pool = ClassPool.getDefault();
        ClassClassPath classPath = new ClassClassPath(cls);
        pool.insertClassPath(classPath);

        CtClass cc = pool.get(clazzName);
        CtMethod cm = cc.getDeclaredMethod(methodName);
        MethodInfo methodInfo = cm.getMethodInfo();
        CodeAttribute codeAttribute = methodInfo.getCodeAttribute();
        LocalVariableAttribute attr = (LocalVariableAttribute) codeAttribute.getAttribute(LocalVariableAttribute.tag);
        String[] paramNames = new String[cm.getParameterTypes().length];
        if (attr != null) {
            // exception
            int pos = Modifier.isStatic(cm.getModifiers()) ? 0 : 1;
            for (int i = 0; i < paramNames.length; i++) {
                paramNames[i] = attr.variableName(i + pos); //paramNames即参数名
            }
        }
        return paramNames;
    }

}
