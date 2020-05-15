package com.example.demo;

import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 14:40
 **/
public class Util {

    public static ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 200, 60, TimeUnit.SECONDS,
            new SynchronousQueue<>(), new ThreadFactory() {
        @Override
        public Thread newThread(Runnable r) {
            return new Thread(r, "test");
        }
    });
}
