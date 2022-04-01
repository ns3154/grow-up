package com.example.demo;

import org.junit.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/03/25 16:12
 **/
public class Slot {

    @Test
    public void test () {

        long heap = Runtime.getRuntime().totalMemory() / 1024 / 1024;
        long max = Runtime.getRuntime().maxMemory() / 1024 / 1024;
        System.out.println(heap);
        System.out.println(max);
    }

}
