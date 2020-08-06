package com.example.demo.collection;

import com.sun.org.apache.regexp.internal.RE;
import org.junit.Before;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.function.Predicate;
import java.util.function.UnaryOperator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/06 09:21
 **/
public class ListTest {



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
