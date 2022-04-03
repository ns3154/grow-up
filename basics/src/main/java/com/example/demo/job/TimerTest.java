package com.example.demo.job;

import java.util.Timer;
import java.util.TimerTask;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/04/04 01:10
 **/
public class TimerTest {

    public static void main(String[] args) throws InterruptedException {
        Timer timer = new Timer();

        timer.scheduleAtFixedRate(new TimerTask() {
            @Override
            public void run() {
                // do something
                throw new RuntimeException("test");
                //int i = 1 / 0;
            }
        }, 10000, 1000);

        Thread.sleep(1000000000);
    }


}
