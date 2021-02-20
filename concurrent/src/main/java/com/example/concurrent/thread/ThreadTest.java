package com.example.concurrent.thread;


import org.junit.jupiter.api.Test;

import java.util.concurrent.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/08 14:24
 **/
public class ThreadTest {


    /**
     * 实现 Runnable 接口
     * 实现 Callable 接口
     * 继承 Thread 类
     * @author 杨帮东
     * @param
     * @since 1.0
     * @date 2020/5/8 14:30
     * @return void
     * @throws
     */
    @Test
    public void test() throws ExecutionException, InterruptedException {
        // 实现 Runnable 接口
        Thread thread = new Thread(() -> System.out.println("runnable()"));
        thread.start();

        // 继承 Thread 类
        Extend extend = new Extend();
        extend.start();

        // 实现 Callable 接口
        FutureTask<Integer> task = new FutureTask(() -> 1);
        Thread thread1 = new Thread(task);
        thread1.start();
        System.out.println(task.get());
    }

    class  Extend extends Thread {
        @Override
        public void run() {
            System.out.println("extend");
        }
    }

    @Test
    public void executor() {
        Executor executor = Executors.newSingleThreadExecutor();
    }


}
