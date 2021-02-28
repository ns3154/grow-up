package org.example.test;

import java.util.Arrays;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/14 10:51
 **/
public class Test {

	@org.junit.jupiter.api.Test
    public void arrayTest() {
        String[] s = new String[0];
        String[] strings = Arrays.copyOf(s, 2);
        strings[0] = "2";
        strings[1] = "2";

        for (String string : strings) {
            System.out.println(string);
        }
    }

	@org.junit.jupiter.api.Test
    public void CopyOnWriteArrayListTest() {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(2);
        list.get(1);
    }

	@org.junit.jupiter.api.Test
    public void concurrentHashMap() {
        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>(16);
        map.put("aa", "aa");
        map.put("bb", "bb");
        map.put("cc", "bb");

        map.size();
        map.isEmpty();
    }

}
