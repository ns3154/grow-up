package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.lang.ref.SoftReference;
import java.lang.ref.WeakReference;
import java.util.HashMap;

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


    public static void main(String[] args) {

    }


    @org.junit.Test
    public void reference() {
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

    @org.junit.Test
    public void hashMap() {
        HashMap<Key, String> map = new HashMap<>();
        Key k1 = new Key(1);
        Key k2 = new Key(1);
        Key k3 = new Key(1);
        Key k4 = new Key(1);
        Key k5 = new Key(1);
        Key k6 = new Key(1);
        Key k7 = new Key(1);
        Key k8 = new Key(1);
        Key k9 = new Key(1);
        Key k10 = new Key(1);
        Key k11 = new Key(1);
        map.put(k1, "k1");
        map.put(k2, "k2");
        map.put(k3, "k3");
        map.put(k4, "k4");
        map.put(k5, "k5");
        map.put(k6, "k6");
        map.put(k7, "k7");
        map.put(k8, "k8");
        map.put(k9, "k9");
        map.put(k10, "k10");
        map.put(k11, "k11");
    }


    static class Key {

        private Integer num;

        public Key() {
            // nothing
        }

        public Key(Integer num) {
            this.num = num;
        }

        public Integer getNum() {
            return num;
        }

        public void setNum(Integer num) {
            this.num = num;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) {
                return true;
            }
            if (!(o instanceof Key)) {
                return false;
            }

            Key key = (Key) o;

            return num != null ? num.equals(key.num) : key.num == null;
        }

        @Override
        public int hashCode() {
            return num != null ? num.hashCode() : 0;
        }
    }

}
