package com.example.async.config;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.aop.interceptor.AsyncUncaughtExceptionHandler;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.AsyncConfigurerSupport;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;


/**
 * <pre>
 *      另一种实现, extends {@link AsyncConfigurerSupport}
 *      不建议这样使用
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 15:30
 **/
@Configuration
@ConditionalOnMissingBean(AsyncExecutorConfig.class)
public class MyAsyncExecutorConfig  extends AsyncConfigurerSupport {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private static final int CORE_SIZE = Runtime.getRuntime().availableProcessors() * 2;

    private static final int MAX_POOL_SIZE = 20;

    private static final long KEEP_ALIVE_TIME = 60L;

    public MyAsyncExecutorConfig() {
        super();
    }

    @Override
    public Executor getAsyncExecutor() {
        return new ThreadPoolExecutor(CORE_SIZE, MAX_POOL_SIZE, KEEP_ALIVE_TIME,
                TimeUnit.SECONDS, new LinkedBlockingDeque<>(200),
                new BasicThreadFactory.Builder().namingPattern(Joiner.on("-")
                        .join("AsyncTask-extends", "%s")).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }

    @Override
    public AsyncUncaughtExceptionHandler getAsyncUncaughtExceptionHandler() {
        return (ex, method, params) -> logger.error("** method:{}, params:{}, error:{}", method, params, ex);
    }
}
