package com.example.async.service;

import com.example.async.annotation.MyAsync;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.concurrent.CompletableFuture;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 19:28
 **/
@Configuration
@Async("myExecutor")
//@MyAsync("myExecutor")
public class AsyncTask {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public void asyncTest(String s) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt(s);
        logger.error("********** test :{}*********", s + "asyncTest");
    }

    public void abc(String s) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        int i = Integer.parseInt(s);
        logger.error("********** test :{}*********", s + "abc");
    }


}
