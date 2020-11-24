package com.example.demo;

import com.sun.jmx.snmp.tasks.ThreadService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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

    private static final int HASH_INCREMENT = 1640531527;

    private static final ThreadLocal<String> threadLocal = new ThreadLocal<>();

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            threadLocal.set("222");
        }).start();
        new Thread(() -> {
            threadLocal.set("111");
        }).start();

        Thread.sleep(100000000);

    }




}
