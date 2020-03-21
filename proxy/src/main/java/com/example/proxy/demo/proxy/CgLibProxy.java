package com.example.proxy.demo.proxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

/**
 * <pre>
 *      cglib动态代理无需实现接口，
 *      通过生成子类字节码来实现，比反射快一点，没有性能问题。
 *      但是由于cglib会继承被代理类，需要重写被代理方法，
 *      所以被代理类不能是final类，被代理方法不能是final。
 *
 * 因此，cglib的应用更加广泛一点。
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:43
 **/
public class CgLibProxy implements MethodInterceptor {

    private Logger logger = LoggerFactory.getLogger(getClass());

//    private Object target;

    public Object getInstance(Object object) {
//        this.target = object;
        Enhancer enhancer = new Enhancer();
//        enhancer.setSuperclass(this.target.getClass());
        enhancer.setSuperclass(object.getClass());
        // 设置回调方法
        enhancer.setCallback(this);
        // 创建代理对象
        return enhancer.create();
    }

    @Override
    public Object intercept(Object o, Method method, Object[] objects, MethodProxy methodProxy) throws Throwable {
        logger.info("*** 开始执行 cglib, 执行方法:{}, 执行参数:{} *****", method, objects);
        Object result = methodProxy.invokeSuper(o, objects);
        logger.info("*** 执行完成 cglib, 返回参数:{}", result);
        return result;
    }
}
