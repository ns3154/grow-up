package com.example.demo.concurrent;

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
 * @date 2020/05/14 15:54
 **/
public class AwaitSignalTest {

    public static void main(String[] args) {
        ReentrantLock lock = new ReentrantLock();
        Condition condition = lock.newCondition();
        A a = new A(lock, condition);
        B b = new B(lock, condition);

        new Thread(() -> a.a()).start();
        try {
            Thread.sleep(2000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        new Thread(() -> b.b()).start();
    }


    static class A {

        private final Logger logger = LoggerFactory.getLogger(getClass());

        private final ReentrantLock lock;
        private final Condition condition;

        public A(ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }

        private void a() {
            lock.lock();
            try {
                logger.info("a() 进入await().....");
                condition.await();
                logger.info("a() 被唤醒.....");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                logger.info("a() 释放锁.....");
            }
        }

    }

    static class B {
        private final Logger logger = LoggerFactory.getLogger(getClass());
        private final ReentrantLock lock;
        private final Condition condition;

        public B(ReentrantLock lock, Condition condition) {
            this.lock = lock;
            this.condition = condition;
        }

        private void b() {
            lock.lock();
            try {
                logger.info("进入b() 方法,准备唤醒A.a()....");
                condition.signal();
                logger.info("b方法执行完唤醒...");
            } finally {
                lock.unlock();
                logger.info("b方法释放锁...");
            }
        }
    }


}
