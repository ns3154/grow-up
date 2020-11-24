package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.LockSupport;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/17 14:01
 **/
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {
        ThreadLocal<String> threadLocal = new ThreadLocal<>();
        ThreadLocal<String> threadLocal1 = new ThreadLocal<>();
        ThreadLocal<String> threadLocal2 = new ThreadLocal<>();
        threadLocal1.set("111");
        threadLocal2.set("222");
        threadLocal.set("000");
        ThreadLocal<String> threadLocal3 = new ThreadLocal<>();
        threadLocal3.set("3333");
        threadLocal3.get();

    }


}
