package com.example.demo.concurrent;

import java.util.Random;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/10 09:50
 **/
public class Shop {

    public Future<Double> getPrice(String product) {
        CompletableFuture<Double> cancelPrice = new CompletableFuture<>();
        new Thread(() -> cancelPrice.complete(calculatePrice(product))).start();


        return cancelPrice;
    }

    public double calculatePrice(String product) {
        delay();
        return new Random().nextDouble() * product.charAt(0) + product.charAt(1);
    }

    // 模拟延迟 1s
    public static void delay() {
        try {
            TimeUnit.SECONDS.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
