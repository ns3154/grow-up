package com.example.concurrent;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.CountDownLatch;
import java.util.function.Consumer;

public class CountDownTest {

    public static void main(String[] args) {
        List<Integer> nums = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            nums.add(i);
        }

        CountDownLatch latch = new CountDownLatch(nums.size());

        nums.parallelStream().forEach(i -> {
            System.err.println(String.format("count=%s, curthread=%s", i, Thread.currentThread().getName()));
            latch.countDown();
        });

        try {
            latch.await();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        System.err.println(latch.getCount());
        System.err.println("完成");

        BigDecimal decimal = new BigDecimal("0.02");
    }
}
