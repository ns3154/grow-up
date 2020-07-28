package com.example.demo.stream;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/25 18:47
 **/
public class StreamTest {

    @Test
    public void forEach() {
        List<Integer> list = new ArrayList<>();

        for (int i = 1; i < 100;i++) {
            list.add(i);
        }

        int sum = list.stream().mapToInt(i -> i).sum();
        int sum1 = list.parallelStream().mapToInt(i -> i).sum();

        System.out.println(sum);
        System.out.println(sum1);
    }
}
