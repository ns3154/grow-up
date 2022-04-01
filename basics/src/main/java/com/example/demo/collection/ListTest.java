package com.example.demo.collection;

import org.junit.jupiter.api.Test;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/06 09:21
 **/
public class ListTest {

    private Integer z = 3;


    @Test
    public void copyOnWriteList () {
        CopyOnWriteArrayList<Integer> list = new CopyOnWriteArrayList<>();
        list.add(1);
        list.add(1);
        list.add(1, 1);
        list.remove(new Integer(1));
        list.addIfAbsent(new Integer(3));
    }



    @Test
    public void remove() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");

        List<String> removeList = new ArrayList<>();
        removeList.add("a");
        list.removeAll(removeList);
        list.removeIf("d"::equals);

        list.forEach(System.out::println);
    }

    @Test
    public void replaceAll() {
        List<String> list = new ArrayList<>();
        list.add("a");
        list.add("b");
        list.add("c");
        list.add("d");
        list.replaceAll(s -> s + "1");
        list.forEach(System.out::println);
    }
}
