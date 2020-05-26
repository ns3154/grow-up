package com.example.demo.concurrent;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/14 16:33
 **/
public class CyclicBarrierTest {

    private final static Logger logger = LoggerFactory.getLogger(CyclicBarrierTest.class);

    static AtomicInteger integer = new AtomicInteger(0);

    public static void main(String[] args) throws InterruptedException {
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 10, 60,
                TimeUnit.SECONDS, new SynchronousQueue<>(), r -> new Thread(r, "test"));

        int threadSize = 5;
        CyclicBarrier cb = new CyclicBarrier(threadSize, () -> {
            logger.error("开始执行前 先执行该方法");
        });

        cyclic(cb, executor, threadSize);

        while (integer.get() != threadSize) {
            // noting
        }


//        cb.reset();
        TimeUnit.SECONDS.sleep(3);
        logger.error("------------------------------------");
        cyclic(cb, executor, threadSize);

        executor.shutdown();

    }

    private static void cyclic(CyclicBarrier cb, Executor executor, int threadSize) {
        logger.error("开始");
        integer.set(0);
        for (int i = 0; i < threadSize;i++) {
            int finalI = i;
            executor.execute(() -> {
                try {
                    logger.error("执行await前:{}", finalI);
                    cb.await();
                    integer.addAndGet(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (BrokenBarrierException e) {
                    e.printStackTrace();
                }
                logger.error("执行完成:{}", finalI);
            });
        }
    }
}
