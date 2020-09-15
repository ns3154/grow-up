package com.example.demo;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/02 15:49
 **/
public class MapTest {

    public static void main(String[] args) {

        Map<String, Model> map = new ConcurrentHashMap<>();
        Model model = map.computeIfAbsent("ked", key -> new Model(4545));
//        Model mode2 = map.computeIfAbsent("ked", key -> new Model(4546));


        Model model1 = map.computeIfPresent("ked", (key, value) -> {
//            System.out.println(value.getCode());
            return new Model(2322);
        });

        String ss = "929870599880241251";
        String substring = ss.substring(17, 18);
        String sss = ss.substring(ss.length() - 1);
        System.out.println(substring);
        System.out.println(sss);
    }
}
