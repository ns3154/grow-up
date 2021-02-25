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
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60, TimeUnit.SECONDS, new SynchronousQueue<>(), r -> new Thread(r, "test"));
        CountDownLatch cdl = new CountDownLatch(5);
        logger.error("开始");
        for (int i = 0;i < 5;i++) {
            int finalI = i;
	        final int finalI1 = i;
	        executor.execute(() -> {



                cdl.countDown();
		        while (finalI1 == 3) {
			        Thread.currentThread().interrupt();
		        }
                logger.error("执行:{}", finalI);

            });
        }

        cdl.await();

        logger.error("执行完毕...");
        executor.shutdown();


    }

}
