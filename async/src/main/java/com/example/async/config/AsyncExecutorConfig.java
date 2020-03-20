package com.example.async.config;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.context.annotation.AdviceMode;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.lang.reflect.Method;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 19:26
 **/
@Configuration
@EnableAsync(mode = AdviceMode.ASPECTJ)
public class AsyncExecutorConfig implements AsyncConfigurer {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final int MAX_POOL_SIZE = 20;

    private static final long KEEP_ALIVE_TIME = 60L;

    @Bean
    public Executor asyncTest() {
        return new ThreadPoolExecutor(CORE_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(200),
                new BasicThreadFactory.Builder().namingPattern(Joiner.on("-")
                        .join("asyncTest", "%s")).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> logger.error("** method:{}, params:{}, error:{}", method, params, ex);
    }
}
