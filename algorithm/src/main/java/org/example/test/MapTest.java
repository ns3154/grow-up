package org.example.test;

import org.junit.Test;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeSet;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/27 18:57
 **/
public class MapTest {


    @Test
    public void linkedHashMapTest() {
//         顺序打印
        Map<Integer, Integer> linked = new LinkedHashMap<>();
        linked.put(1, 11);
        linked.put(7, 11);
        linked.put(9, 11);
        linked.put(2, 11);
        linked.put(3, 11);
        linked.forEach((k, v) -> System.out.println(k));

        System.out.println("----------------------------");

        /**
         * 按照访问时间排序 先访问的 放在后边
         */
        Map<Integer, Integer> access = new LinkedHashMap<>(10, 0.75f, true);
        access.put(1, 11);
        access.put(7, 11);
        access.put(9, 11);
        access.put(2, 11);
        access.put(3, 11);

        access.put(9, 18);
        access.get(7);
        access.get(1);

        System.out.println("迭代");
        for (Map.Entry<Integer, Integer> entry : access.entrySet()) {
            System.out.println(entry.getKey());
        }
    }
}
