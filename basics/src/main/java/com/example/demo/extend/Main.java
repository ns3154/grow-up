package com.example.demo.extend;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/01 20:57
 **/
public class Main {

    public static void main(String[] args) throws InterruptedException {
        A a = new A("a", 1000L);
        B b = new B("b", 2000L);

        new Thread(() -> {
            a.sss();
        }).start();

        new Thread(() -> {
            b.sss();
        }).start();

        Thread.sleep(10000);

        System.out.println("A:" + a.first);
        System.out.println("B:" + b.first);

        System.out.println("A:" + A.sFirst);
        System.out.println("B:" + B.sFirst);

        System.out.println("A:" + a.list.size() + ":" + a.list.get(0).getName());
        System.out.println("B:" + b.list.size() + ":" + b.list.get(0).getInteger());
    }
}
