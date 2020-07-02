package com.example.demo.extend;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/07/01 20:54
 **/
public abstract class BaseTest<T> {

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseTest.class);

    protected static final int COPY_AFTER_CLEAN = 1;

    private final ReentrantReadWriteLock lock = new ReentrantReadWriteLock(true);

    protected final Lock writeLock = lock.writeLock();

    protected final ArrayList<T> list = new ArrayList<>(300);


    protected String first;

    protected static String sFirst;

    public BaseTest() {

    }

    public BaseTest(String name, Long time) {
        Thread daemon = new Thread(() -> {
            while (true) {
                try {
                    LOGGER.info("name:{}", name);
                    TimeUnit.MILLISECONDS.sleep(time);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        daemon.setDaemon(true);
        daemon.setName(name + "-check-thread");
        daemon.start();
    }

}
