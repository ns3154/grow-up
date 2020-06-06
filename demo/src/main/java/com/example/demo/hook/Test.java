package com.example.demo.hook;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/27 18:16
 **/
public class Test {

    private static Map<String, InterFaceTest<?>> map = new HashMap<>();

    public static void main(String[] args) throws InterruptedException {
        add("ss",() -> getO("sss"));
        TimeUnit.SECONDS.sleep(2);
        InterFaceTest<?> a = map.get("a");
        TimeUnit.SECONDS.sleep(2);
        Object object = a.getObject();
        System.out.println("111111111111111" + object.toString());
        TimeUnit.SECONDS.sleep(2);
        Object object1 = a.getObject();
        System.out.println("111111111111111" + object1);

    }

    private static Object getO(Object ssss) {
        System.out.println(System.currentTimeMillis());
        return ssss;
    }

    public static void add(String name, InterFaceTest<?> test) {
        map.put("a", test);
    }

    @org.junit.Test
    public void test() {
        String b = "sss";
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode(b));
        System.out.println(System.identityHashCode("ssss"));
    }
}
