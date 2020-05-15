package com.example.concurrent.model;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/08 15:09
 **/
public class A {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    private final Object lock;

    public A(Object lock) {
        this.lock = lock;
    }



    public void sss() {
        synchronized (lock) {
            logger.info("开始....");
            try {
                lock.wait();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.info("结束...");
        }
    }

    public void release() {
        lock.notify();
    }


}
