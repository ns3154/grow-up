package com.example.demo.lock;

import com.example.demo.Util;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 14:40
 **/
public class LockTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void lockTest() throws InterruptedException {

        Lock lock = new ReentrantLock();
        Util.executor.execute(() -> {
            logger.error("加锁1");
            lock.lock();
            logger.error("获得到锁1");
            try {
                TimeUnit.SECONDS.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("error:{}", e);
            } finally {
                lock.unlock();
            }

        });


        Util.executor.execute(() -> {
            logger.error("准备加锁2");
            lock.lock();
            logger.error("获得到锁2");
            lock.unlock();
            logger.error("释放锁2");

        });


        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void lockInterruptiblyTest() throws InterruptedException {
        Lock lock = new ReentrantLock();

        Util.executor.execute(() -> {
            logger.error("准备加锁1");
            try {
                Thread.currentThread().interrupt();
                lock.lockInterruptibly();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            logger.error("获得到锁1");
            try {

                TimeUnit.SECONDS.sleep(20);
            } catch (InterruptedException e) {
                e.printStackTrace();
                logger.error("error:{}", e);
            } finally {
                if (!Thread.currentThread().isInterrupted()) {
                    lock.unlock();
                    logger.error("释放锁1");
                }


            }

        });

        TimeUnit.SECONDS.sleep(1);

        Util.executor.execute(() -> {
            logger.error("准备加锁2");
            try {
                lock.lockInterruptibly();
                logger.error("获得到锁2");
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                lock.unlock();
                logger.error("释放锁2");
            }
        });


        TimeUnit.SECONDS.sleep(100);
    }

    @Test
    public void tryLockTest() throws InterruptedException {
        ReentrantLock lock = new ReentrantLock();

        Util.executor.execute(() -> {
            if (lock.tryLock()) {
                try {
                    logger.error("获取到锁 1");
                    TimeUnit.SECONDS.sleep(10);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    lock.unlock();
                    logger.error("释放锁1");
                }
            } else {
                logger.error("没有拿到锁1");
            }

        });

        TimeUnit.SECONDS.sleep(1);
        Util.executor.execute(() -> {
            try {
                if (lock.tryLock(25, TimeUnit.SECONDS)) {
                    try {
                        logger.error("获取到锁 2");
                    } finally {
                        lock.unlock();
                        logger.error("释放锁2");
                    }
                } else {
                    logger.error("没有拿到锁2");
                }
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        });

        TimeUnit.SECONDS.sleep(100);




    }


    @Test
    public void lockShared() throws InterruptedException {
        ReadWriteLock lock = new ReentrantReadWriteLock();
        Lock read = lock.readLock();
        Lock write = lock.writeLock();

        List<Integer> list = new ArrayList<>();
        Util.executor.execute(() -> {
            write.lock();
            try {
                logger.error("write 1 lock");
                list.add(1);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                write.unlock();
                logger.error("write 1 unlock");
            }
        });

        Util.executor.execute(() -> {
            read.lock();
            try {
                logger.error("read 1 lock");
                logger.error("size:{}", list.size());
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                read.unlock();
                logger.error("read 1 unlock");
            }
        });
        TimeUnit.SECONDS.sleep(1);

        Util.executor.execute(() -> {
            write.lock();
            try {
                logger.error("write 2 lock");
                list.add(1);
                TimeUnit.SECONDS.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            } finally {
                write.unlock();
                logger.error("write 2 unlock");
            }
        });

        for (;;) {
            TimeUnit.SECONDS.sleep(1);
            Util.executor.execute(() -> {
                read.lock();
                try {
                    logger.error("read 2 lock");
                    logger.error("size:{}", list.size());
                }finally {
                    read.unlock();
                    logger.error("read 2 unlock");
                }
            });
        }

    }
}
