package com.example.demo.juc;

import com.example.demo.Util;

import java.util.concurrent.Semaphore;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/15 16:41
 **/
public class SemaphoreTest {

    public static void main(String[] args) {
        Semaphore semaphore = new Semaphore(3);
        for (int i = 0;i < 111;i++) {
            Util.executor.execute(() ->{
                try {
                    semaphore.acquire();
                    System.out.println("获取到资源");
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    System.out.println("释放资源-----------------------------");
                    semaphore.release();
                }
            });
        }
    }
}
