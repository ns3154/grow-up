package com.example.concurrent.model;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/02/23 19:07
 **/
public class B {

    public synchronized void b1() {
        try {
            System.out.println("b1");
            Thread.sleep(10000l);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public synchronized void b2() {
        System.out.println("b2");
    }

    public synchronized static void b3() {
        System.out.println("b3");
    }

    public static void main(String[] args) throws InterruptedException {
        B b = new B();

        new Thread(() -> b.b1()).start();
        Thread.sleep(2000);
        System.out.println("执行b2,b3方法");
        new Thread(() -> b.b2()).start();
        new Thread(() -> b.b3()).start();

        Hashtable<String, String> table = new Hashtable<>();
        Set<Map.Entry<String, String>> entries = table.entrySet();
        Iterator<Map.Entry<String, String>> iterator = entries.iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, String> next = iterator.next();
        }
    }
}
