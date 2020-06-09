package com.example.demo;

import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/06/10 01:39
 **/
public class GotoTest {

    public static void main(String[] args) throws InterruptedException {
        int i = 0;
        key:
        for (;;) {
            TimeUnit.SECONDS.sleep(1);
            System.out.println("最外层循环:" + i);
            if (i < 10) {
                i++;
                System.out.println("哒哒哒哒:" + i);
                continue key;
            }
            if (i == 10) {
                break key;
            }
        }



        System.out.println("跳出循环了" + i);

    }
}
