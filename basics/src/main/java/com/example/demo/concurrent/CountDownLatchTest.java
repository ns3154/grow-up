package com.example.demo.concurrent;

import org.junit.platform.commons.function.Try;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/14 16:14
 **/
public class CountDownLatchTest {

    private final static Logger logger = LoggerFactory.getLogger(CountDownLatchTest.class);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), r -> new Thread(r, "test"));
        CountDownLatch cdl = new CountDownLatch(5);
        logger.error("开始");
        List<Future<?>> list = new ArrayList<>();
        for (int i = 0;i < 5;i++) {
            int finalI = i;
	        final int finalI1 = i;
            Future<?> submit = executor.submit(() -> {
                try {
                    Thread.sleep(1000L);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                if (finalI1 == 2 || finalI1 == 3) {
//                    try {
//                        Thread.sleep(5000000L);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                if (finalI1 == 4) {
//                    Thread.currentThread().interrupt();
//                }
                cdl.countDown();
                logger.error("执行:{}", finalI);

            });
            list.add(submit);
        }
//        final Thread thread = Thread.currentThread();
//        executor.execute(() -> {
//            int index = 0;
//            while (index++ < 3) {
//                try {
//                    Thread.sleep(1000);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            thread.interrupt();
//        });
        logger.error("running -  count:{}", cdl.getCount());
        try {
            cdl.await();
        } catch (InterruptedException e) {
            logger.error("被中断唤醒");
        }

        logger.error("running count:{}", cdl.getCount());

        executor.shutdown();



    }

}
