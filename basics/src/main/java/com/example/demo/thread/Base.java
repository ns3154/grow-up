package com.example.demo.thread;

import org.junit.Test;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/16 16:07
 **/
public class Base {

    Object lock = new Object();

    @Test
    public void interruptTest() {
        Thread t = new Thread(() -> {
            boolean b = true;
            while (b) {
                System.out.println(".....");
                try {
                    Thread.sleep(5000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    b = false;
                }
            }
            System.out.println("退出线程.......");
        });
        t.start();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        t.interrupt();

        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void synchronizedTest() {
        SynchronizedModel m = new SynchronizedModel();
        SynchronizedModel m1 = new SynchronizedModel();
        new Thread(() -> m.f1()).start();
        new Thread(() -> m1.f1()).start();
        new Thread(() -> SynchronizedModel.f2()).start();
        new Thread(() -> m.f1()).start();
        new Thread(() -> SynchronizedModel.f2()).start();
        new Thread(() -> m.f3()).start();


        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}
