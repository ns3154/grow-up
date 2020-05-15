package com.example.demo.singleton;

import java.io.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 18:14
 **/
public class SingletonTest {

    public static void main(String[] args) throws IOException, ClassNotFoundException {
        String local = "C:\\temp\\singleton";
        Singleton singleton = Singleton.getSingleton();
        ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(local));
        oos.writeObject(singleton);
        System.out.println("序列化完成");
        File file = new File(local);
        ObjectInputStream ois =  new ObjectInputStream(new FileInputStream(file));
        Singleton newInstance = (Singleton) ois.readObject();
        //判断是否是同一个对象
        System.out.println(newInstance == singleton);
        System.out.println(newInstance.hashCode());
        System.out.println(singleton.hashCode());
    }
}
