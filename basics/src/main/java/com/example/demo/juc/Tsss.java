package com.example.demo.juc;


import java.util.Objects;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/24 19:11
 **/
public class Tsss {

    private final Lock lock = new ReentrantLock();

    private final Condition condition = lock.newCondition();

    private volatile String s;

    public void print() {
        Thread a = new Thread(() ->  print("a"));
        Thread b = new Thread(() -> print("b"));
        Thread c = new Thread(() ->   print("c"));
        c.start();
        b.start();
        a.start();
        String xx = s;
    }

    public void print(String str) {
        lock.lock();
        try {
            switch (str) {
                case "a" : {
                    System.out.println("a");
                    s = "b";
                    condition.signalAll();
                    return;
                }
                case "b" : {
                    if (Objects.equals(s, str)) {
                        System.out.println("b");
                        s = "c";
                        condition.signalAll();
                        return;
                    } else {
                        condition.await();
                        print(str);
                    }
                    return;
                }
                case "c" : {
                    if (Objects.equals(s, str)) {
                        System.out.println("c");
                        s = "c";
                        condition.signalAll();
                        return;
                    } else {
                        condition.await();
                        print(str);
                    }
                    return;
                }
                default: {}
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            lock.unlock();
        }

    }


    public static void sss () {
    }



}
