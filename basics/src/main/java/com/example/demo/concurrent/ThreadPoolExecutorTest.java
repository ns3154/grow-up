package com.example.demo.concurrent;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

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
    public void test() {
        ThreadMXBean threadMXBean = ManagementFactory.getThreadMXBean();
        ThreadPoolExecutor executor = new ThreadPoolExecutor(5, 30, 10, TimeUnit.SECONDS,
                new SynchronousQueue<>(),
                new BasicThreadFactory.Builder()
                        .namingPattern(Joiner.on("-")
                                .join("test-thread-pool", "%s"))
                        .build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
        int i = 0;
        for (;;) {
            executor.execute(() -> {
//                try {
//                    TimeUnit.MILLISECONDS.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
                logger.info("当前线程名:{}, 活跃线程数:{}, 当前线程数:{}, 线程总数:{}", Thread.currentThread().getName(),
                        executor.getActiveCount(), executor.getPoolSize(), threadMXBean.getTotalStartedThreadCount());
            });

            if (i > 25) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            ++i;
        }

    }
}
