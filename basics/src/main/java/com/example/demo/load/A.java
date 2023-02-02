package com.example.demo.load;

import java.util.Random;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东
 * @date 2021/02/22 11:12
 * @since 1.0
 **/
public class A {

    public static int s = 333;

    public static final int a = 33322;

    public final static B b = new B();

    public static final Double db = new Random().nextDouble();

    static {
        System.out.println("A init");
        System.out.println(s);
        System.out.println(b);
        System.out.println(db);
    }



    static class T {

        static {
            System.out.println("T init");
        }

        static int dddd = 3;
    }

    public interface sss {

        static int sssst = 33;

    }

    public interface sssF {

    }
}
