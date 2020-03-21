package com.example.proxy.demo.proxy;

import com.alibaba.fastjson.JSON;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

/**
 * <pre>
 *    jdk动态代理会根据被代理对象生成一个继承了Proxy类，
 *    并实现了该业务接口的jdk代理类，该类的字节码会被传进去的ClassLoader加载，
 *    创建了jdk代理对象实例，jdk代理对象实例在创建时，业务代理对象实例会被赋值给Proxy类，
 *    jdk代理对象实例也就有了业务代理对象实例，
 *    同时jdk代理对象实例通过反射根据被代理类的业务方法创建了相应的Method对象m（可能有多个）。
 *    当jdk代理对象实例调用业务方法，
 *    如proxy.addUser();这个会先把对应的m对象作为参数传给invoke()方法（就是invoke方法的第二个参数），
 *    调用了jdk代理对象实例的invoke()回调方法，在invoke方法里面再通过反射来调用被代理对象的因为方法，
 *    即result = method.invoke(target, args);。
 *
 *
 *    jdk动态代理必须实现接口，通过反射来动态代理方法，消耗系统性能。但是无需产生过多的代理类，避免了重复代码的产生，系统更加灵活。
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:24
 **/
public class JdkProxy implements InvocationHandler {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private Object target;

    public JdkProxy(Object object) {
        this.target = object;
    }

    public Object getProxy() {
        return Proxy.newProxyInstance(Thread.currentThread().getContextClassLoader(),
                this.target.getClass().getInterfaces()
                , this);
    }

    @Override
    public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
        logger.info("*** JdkProxy 开始执行 ***********");
        Object invoke = method.invoke(target, args);
        logger.info("*** JdkProxy 执行结束,返回参数:{}", JSON.toJSONString(invoke));
        return invoke;
    }
}
