package com.example.async.service;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 19:28
 **/
@Component
@Async("asyncTest")
public class AsyncTask {

    private Logger logger = LoggerFactory.getLogger(getClass());


    public String asyncTest(String s) {
        try {
            Thread.sleep(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        logger.info("********** test :{}*********", s + "asyncTest");
        return s + "asyncTest";
    }



}
