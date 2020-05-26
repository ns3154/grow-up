package com.example.concurrent.thread;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *      如果在main线程中创建了一个守护线程，
 *      当main方法运行完毕之后，守护线程也会随着消亡
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 19:44
 **/
public class Deamon {

    private static Logger logger = LoggerFactory.getLogger(Deamon.class);

    public static void main(String[] args) throws InterruptedException {
        Thread thread1 = new Thread(() -> test(1));
        Thread thread2 = new Thread(() -> test(2));
        thread1.setDaemon(true);
        thread1.setName("thread_name");
        thread1.setPriority(5);
        thread1.start();


    }

    public static int test(int i) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.error("执行test方法了, {}" , i);
        System.out.println("守护线程, 当main方法执行完毕,该线程也随之消亡,所以此处的话不会打印出来的");
        return i++;
    }
}
