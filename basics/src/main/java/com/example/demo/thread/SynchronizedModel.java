package com.example.demo.thread;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/16 16:20
 **/
public class SynchronizedModel {

    public synchronized void f1() {
        System.out.println("进入f1.................");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }



    public synchronized static void f2() {
        System.out.println("进入f2.................");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void f3() {
        System.out.println("into f3.................");
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void f4() {
        synchronized (this) {
            System.out.println("into f4........");
        }
    }
}
