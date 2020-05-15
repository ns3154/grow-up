package com.example.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), new ThreadFactory() {
            @Override
            public Thread newThread(Runnable r) {
                return new Thread(r, "test");
            }
        });
        CountDownLatch cdl = new CountDownLatch(5);
        logger.info("开始");
        for (int i = 0;i < 5;i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                cdl.countDown();
                logger.info("执行:{}", finalI);

            });
        }

        cdl.await();

        logger.info("执行完毕...");
        executor.shutdown();


    }

}
