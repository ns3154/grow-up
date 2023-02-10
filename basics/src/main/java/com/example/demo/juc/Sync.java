package com.example.demo.juc;

/**
 * <p>
 *
 * </p>
 *
 * @author Ns
 * @version 1.0
 * @date 2023/02/08 17:58
 **/
public class Sync {

    Object lock = new Object();

    public  void t() {
        synchronized (lock) {
            int a = 4;
            System.out.println(a);
        }

    }
}
