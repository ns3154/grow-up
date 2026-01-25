package com.example.demo;

import com.example.demo.extend.A;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 16:31
 **/
public class interruptTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public void test() throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {

            }
            logger.error("线程被中F断了");
        });
        thread.start();
        TimeUnit.SECONDS.sleep(5);
        logger.error("准备中断线程");
        thread.interrupt();
    }
}
