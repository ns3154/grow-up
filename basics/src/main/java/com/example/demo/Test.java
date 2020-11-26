package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.CommandLineRunner;

import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
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



    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 2, 60L, TimeUnit.SECONDS,
                new LinkedBlockingDeque<>(200),Thread::new, new ThreadPoolExecutor.AbortPolicy());
        executor.execute(() -> System.out.println(222222));

        // 11100000 00000000 00000000 00000000
        // 00011111 11111111 11111111 11111111
        // 00000000 00000000 00000000 00000000
        System.out.println(-1 & ~536870911);

    }



}
