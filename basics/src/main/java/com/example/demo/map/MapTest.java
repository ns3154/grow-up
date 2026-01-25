package com.example.demo.map;


import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.function.BiFunction;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/06 09:50
 **/
public class MapTest {

    public void mapTest() {
        Map<String, String> map = new HashMap<>();
        map.put("aang", "ssds");
        map.put("bang", "ssds");
        map.put("cang", "ssds");
        map.put("dang", "ssds");
        map.get("sdfsf");
        map.remove("asdfasf");



        map.forEach((k, v) -> System.out.println(k + ":" + v));

        System.out.println("------------- 按照key 倒叙  ---------------");
        map.entrySet().stream().sorted(Map.Entry.comparingByKey(Comparator.reverseOrder())).forEachOrdered(System.out::println);
    }

    public void merge() {
        Map<String, String> one = new HashMap<>();
        one.put("a", "1");
        one.put("b", "2");
        one.put("c", "3");
        one.put("d", "5");


        Map<String, String> two = new HashMap<>();
        two.put("d", "5");
        two.put("f", "6");
        two.put("g", "7");
        two.put("h", "8");

        Map<String, String> merge = new HashMap<>(one);

        // 合并中 如果遇到key一样 就将两个value链接起来
        // merge : 当key 一样时, 就值不为空 才会触发  BiFunction 函数
        two.forEach((k, v) -> {
            String merge1 = merge.merge(k, v, (v1, v2) -> {
                System.out.println("merge:" + v1 + ":" + v2);
                return v1 + "&" + v2;
            });
            System.out.println(k + ":" + v + ":" + merge1);
        });
        merge.forEach((k,v) -> System.out.println(k + ":" + v));

    }


    public void coc() {
        ConcurrentHashMap<String, Long> chm = new ConcurrentHashMap<>();

        for (int i = 0; i <10;i++) {
            chm.put("a" + i, (long) i);
        }

        System.out.println(chm.size());
        System.out.println(chm.get("sdfs"));
        System.out.println(chm.remove("sdfs"));


        // 无锁执行 forkjoin 线程池
        Long aLong = chm.reduceValues(1L, Long::max);
        Long search = chm.search(Long.MAX_VALUE, (s, aLong1) -> {
            System.out.println(s + ":" + aLong1);
            System.out.println(Thread.currentThread().getName());
            return aLong1;
        });

        Long search1 = chm.search(1, (k, v) -> {
            if ("a2".equals(k)) {
                return v;
            }
            return null;
        });

        System.out.println(search1);

        chm.mappingCount();
        chm.size();

        ConcurrentHashMap.KeySetView<String, Long> strings = chm.keySet();
    }
}
