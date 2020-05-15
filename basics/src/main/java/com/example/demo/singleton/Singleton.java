package com.example.demo.singleton;

import org.jetbrains.annotations.Contract;

import java.io.Serializable;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 18:02
 **/
public class Singleton implements Serializable {

    private static final long serialVersionUID = -3856947624300996790L;

    private static volatile Singleton singleton;


    public Singleton() {
        System.out.println("无参构造器实例化...");
    }


    public static Singleton getSingleton() {
        if (singleton == null) {
            synchronized (Singleton.class) {
                if (singleton == null) {
                    System.out.println("单例实例化.........");
                    singleton = new Singleton();
                }
            }
        }
        return singleton;
    }

    private Object readResolve() {
        return singleton;
    }


}
