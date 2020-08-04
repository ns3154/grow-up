package com.example.demo.concurrent;

import ch.qos.logback.core.util.TimeUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/14 15:39
 **/
public class WaitNotifyTest {

    public static void main(String[] args) {
        Object lock = new Object();
        A a = new A(lock);
        B b = new B(lock);

        new Thread(() -> {
            b.b();
        }).start();
        try {
            Thread.sleep(5000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> {
            a.a();
        }).start();

    }

    static class A {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final Object lock;

        public A(Object lock) {
            this.lock = lock;
        }

        private void a() {
            synchronized (lock) {
                logger.error("进入{}, 方法代码块", "a");
                logger.error("a 执行唤醒");
                lock.notify();
            }
        }
    }

    static class B {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final Object lock;

        public B(Object lock) {
            this.lock = lock;
        }

        private void b() {
            synchronized (lock) {
                logger.error("进入{}, 方法代码块", "b");
                try {
                        lock.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                logger.error("b 被唤醒");
            }
        }
    }


}
