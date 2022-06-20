package com.example.demo.juc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAccumulator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/10 19:03
 **/
public class NumberTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    ExecutorService executor = Executors.newFixedThreadPool(10);

    @Test
    public void longTest() throws InterruptedException {
        LongAccumulator la = new LongAccumulator(Long::sum, 0);
        AtomicLong al = new AtomicLong(0);
        for (int i = 0;i < 10;i++) {
            executor.execute(() -> {
                la.accumulate(1);
                al.addAndGet(1);
            });
        }



        TimeUnit.SECONDS.sleep(5);
        System.out.println(la.get());
        System.out.println(al.get());





    }
}
