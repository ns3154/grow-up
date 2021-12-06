package com.example.demo.atomic;

import org.junit.jupiter.api.Test;

import java.util.concurrent.atomic.AtomicLong;
import java.util.concurrent.atomic.LongAdder;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/06 09:40
 **/
public class LongTest {

    @Test
    public void test () {
        AtomicLong al = new AtomicLong(1);
        LongAdder la = new LongAdder();
        al.lazySet(12);
        System.out.println(al.get());
        System.out.println(al.getAndAdd(1));
        la.add(1);
        la.sumThenReset();



    }
}
