package com.example.demo;

import org.jetbrains.annotations.NotNull;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.SmartInitializingSingleton;
import org.springframework.boot.CommandLineRunner;

import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.concurrent.Executors;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/17 14:01
 **/
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);


    public static void main(String[] args) throws InterruptedException {
        Object o = new Object();
        WeakReference<Object> weakReference = new WeakReference<>(o);
        SoftReference<Object> softReference = new SoftReference<>(o);

        System.out.println(o);
        System.out.println(weakReference.get());
        System.out.println(softReference.get());
        o = null;
        System.out.println("---------------------");
        try {
            byte[] bytes = new byte[2 * 1024 * 1024];
            Thread.sleep(10000);
            System.gc();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("强引用" + o);
            System.out.println("软引用" + softReference.get());
            System.out.println("弱引用" + weakReference.get());

        }

    }


}
