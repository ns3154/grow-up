package com.example.demo.juc;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.Exchanger;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/18 15:45
 **/
public class ExchangerTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() throws InterruptedException {
        Exchanger<String> exchanger = new Exchanger<>();
        Test1 test1 = new Test1(exchanger, "test1");
        Test2 test2 = new Test2(exchanger, "test2");

        new Thread(test1).start();
        new Thread(test2).start();

        TimeUnit.SECONDS.sleep(100);
    }

    class Test1 extends Thread {

        Exchanger<String> exchanger;

        String context;

        public Test1(Exchanger<String> exchanger, String context) {
            this.exchanger = exchanger;
            this.context = context;
        }

        @Override
        public void run() {
            logger.error("test1方法交换前数据:{}", context);
            try {
                TimeUnit.SECONDS.sleep(20);
                String exchange = exchanger.exchange(context);
                logger.error("test1方法交换后数据:{}", exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    class Test2 extends Thread {

        Exchanger<String> exchanger;

        String context;

        public Test2 (Exchanger<String> exchanger, String context) {
            this.exchanger = exchanger;
            this.context = context;
        }

        @Override
        public void run() {
            logger.error("test2方法交换前数据:{}", context);
            try {
                String exchange = exchanger.exchange(context);
                logger.error("test2方法交换后数据:{}", exchange);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }


}
