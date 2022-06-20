package com.example.demo.juc;

import org.junit.Test;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/06/16 00:26
 **/
public class ConditionTest {

    static Lock lock = new ReentrantLock();

    static Condition condition = lock.newCondition();

    @Test
    public void simple() throws InterruptedException {

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("执行await之前");
                condition.await();
                System.out.println("await 被唤醒");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                System.out.println("await 释放锁");
            }
        }).start();

        Thread.sleep(3000);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("signal");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }).start();

    }

}
