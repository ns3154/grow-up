package com.example.async.controller;

import com.example.async.service.AsyncService;
import com.example.async.service.AsyncTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 19:09
 **/
@RestController
@RequestMapping("async")
public class AsyncController {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AsyncTask asyncTask;

    @Resource
    private AsyncService asyncService;

    @GetMapping("cglib")
    public Object cglib(String s) {
        asyncTask.abc(s);
        logger.error("********** cglib controller 执行完毕 返回 *********");
        return s;
    }

    @GetMapping("jdk")
    public Object jdk(String s) {
        asyncService.interfaceAsyncTest(s);
        logger.error("********** jdk controller 执行完毕 返回 *********");
        return s;
    }




}
