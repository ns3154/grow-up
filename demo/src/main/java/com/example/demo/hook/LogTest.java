package com.example.demo.hook;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/09/15 15:34
 **/
public class LogTest {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Test
    public void test() {
        int i = 1;
        try {
            int z = i / 0;
        } catch (Exception e) {
            logger.error("出现错误, 入参:{}, {}", i, 0, e);
        }
    }
}
