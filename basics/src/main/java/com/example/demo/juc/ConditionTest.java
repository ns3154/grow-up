package com.example.demo.juc;


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
                System.out.println("加锁....1");
            } finally {
                lock.unlock();
            }
        }).start();

        Thread.sleep(1000);

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("加锁....2");
            } finally {
                lock.unlock();
            }
        }).start();



        new Thread(() -> {

            lock.lock();
            try {
                System.out.println("加锁....3");
            } finally {
                lock.unlock();
            }
        }).start();

        new Thread(() -> {
            lock.lock();
            try {
                System.out.println("signal");
                condition.signal();
            } finally {
                lock.unlock();
            }
        }).start();


        Thread.sleep(100000000L);
    }

    public void noLockError () {

        try {
            condition.await();
            System.out.println("await.......");
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            condition.signal();
        }






    }

}
