package com.example.demo.juc;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/09 18:03
 **/
public class ThreadPoolExecutorTest {

    private final static Logger logger = LoggerFactory.getLogger(ThreadPoolExecutorTest.class);

    @Test
    public void test() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(2, 5, 1000, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new BasicThreadFactory.Builder()
                        .namingPattern(Joiner.on("-")
                                .join("test-thread-pool", "%s"))
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        executor.execute(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        executor.execute(() -> {
//            try {
//                TimeUnit.HOURS.sleep(1);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        });
        try {
            TimeUnit.SECONDS.sleep(2);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        executor.execute(() -> {
            try {
                TimeUnit.HOURS.sleep(1);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });


        try {
            TimeUnit.HOURS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        Future<Object> submit = executor.submit((Callable<Object>) () -> 1);
        Object o = submit.get();
//        int i = 0;
//        for (;;) {
//            executor.execute(() -> {
//                logger.info("当前线程名:{}, 活跃线程数:{}, 当前线程数:{}, 线程总数:{}", Thread.currentThread().getName(),
//                        executor.getActiveCount(), executor.getPoolSize(), threadMXBean.getTotalStartedThreadCount());
//            });
//
//            if (i > 25) {
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//            ++i;
//        }

    }


    @Test
    public void addWorkerNullRunnable () {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                System.out.println(333);
                int i = 3/0;
            }
        });
    }

    @Test
    public void newFixedThreadPool () {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        executorService.execute(() -> System.out.println(1));
        executorService.execute(() -> System.out.println(2));
        executorService.execute(() -> System.out.println(3));
        executorService.execute(() -> System.out.println(4));
    }

    @Test
    public void newSingleThreadExecutor () {
        ExecutorService executorService = Executors.newSingleThreadExecutor(new BasicThreadFactory.Builder()
                .namingPattern(Joiner.on("-")
                        .join("test-thread-pool", "%s"))
                .build());
        executorService.execute(() -> System.out.println(1));
        executorService.execute(() -> System.out.println(2));
        executorService.execute(() -> System.out.println(3));
        executorService.execute(() -> System.out.println(4));

    }
    @Test
    public void newCachedThreadPool () {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(() -> System.out.println(1));
        executorService.execute(() -> System.out.println(2));
        executorService.execute(() -> System.out.println(3));
        executorService.execute(() -> System.out.println(4));
    }

    @Test
    public void newScheduledThreadPool () throws InterruptedException {
        ScheduledExecutorService scheduledExecutorService = Executors.newScheduledThreadPool(1);
        scheduledExecutorService.schedule(() -> System.out.println(1), 10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(2), 10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(3), 10, TimeUnit.SECONDS);
        scheduledExecutorService.schedule(() -> System.out.println(4), 10, TimeUnit.SECONDS);

        Thread.sleep(10000L);
    }

    @Test
    public void bit () {
        int COUNT_BITS = Integer.SIZE - 3;
        System.out.println(2 << COUNT_BITS | 0);

    }
}
