package com.example.wallpaper;

import com.google.common.base.Joiner;
import org.apache.commons.lang3.concurrent.BasicThreadFactory;

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
 * @date 2021/07/10 16:38
 **/
public class Utils {

    private Utils() {
        // ignore
    }

    public static Executor executor(String threadName) {
        return new ThreadPoolExecutor(Runtime.getRuntime().availableProcessors() * 2 + 1, 30,
                60, TimeUnit.SECONDS, new LinkedBlockingDeque<>(200),
                new BasicThreadFactory.Builder().namingPattern(Joiner.on("-")
                        .join(threadName, "%s")).build(),
                new ThreadPoolExecutor.CallerRunsPolicy());
    }
}
