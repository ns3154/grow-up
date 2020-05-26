package com.example.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import javax.annotation.PreDestroy;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/26 14:54
 **/
@Component
public class MyDisposableBean implements DisposableBean {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void destroy() throws Exception {
        logger.error("MyDisposableBean destroy 销毁");
    }

    @PreDestroy
    public void preDestroy() {
        logger.error("MyDisposableBean preDestroy 销毁");
    }
}
