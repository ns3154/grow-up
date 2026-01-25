package com.example.demo.juc;

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

        Thread.sleep(1000L);
        cb.reset();
//        while (integer.get() != threadSize) {
//            // noting
//        }


//        cb.reset();
        TimeUnit.SECONDS.sleep(3);
//        logger.error("------------------------------------");
//        cyclic(cb, executor, threadSize);
//	    TimeUnit.SECONDS.sleep(3);
//	    logger.error("------------------------------------");
	    cyclic(cb, executor, threadSize);
//
//	    TimeUnit.SECONDS.sleep(3);
//	    logger.error("------------------------------------");
//	    cyclic(cb, executor, threadSize);
//
//	    TimeUnit.SECONDS.sleep(3);
//	    logger.error("------------------------------------");
//	    cyclic(cb, executor, threadSize);
//
//	    TimeUnit.SECONDS.sleep(3);
//	    logger.error("------------------------------------");
//	    cyclic(cb, executor, threadSize);

		Thread.sleep(1000000);
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
                    if (finalI == 3) {
                        Thread.sleep(3000);
                    }
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

    public void test() throws InterruptedException {
        CountDownLatch latch = new CountDownLatch(1);
        latch.await();
        latch.countDown();
        System.err.println(222);
        latch.await();
        System.err.println(33);
    }
}
