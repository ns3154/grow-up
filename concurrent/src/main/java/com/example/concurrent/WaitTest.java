package com.example.concurrent;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/08 15:10
 **/
public class WaitTest {

    @Test
    public void test() throws InterruptedException {
        String[] ss = {"s", "d", "d"};
        List<String> collect = Arrays.stream(ss).collect(Collectors.toList());


    }
}
