package com.example.concurrent.pool;

import org.junit.Test;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/11 18:31
 **/
public class ScheduledTest {

    @Test
    public void test() throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(3);
        scheduledExecutorService.scheduleAtFixedRate(new MyThread("scheduleAtFixedRate"), 5, 1, TimeUnit.SECONDS);
        scheduledExecutorService.scheduleWithFixedDelay(new MyThread("scheduleWithFixedDelay"), 5, 1, TimeUnit.SECONDS);
        Thread.sleep(10000000);
    }

    class MyThread  extends Thread {

        String desc;

        public MyThread(String desc) {
            this.desc = desc;
        }

        @Override
        public void run() {
            System.out.println(desc);
        }
    }
}
