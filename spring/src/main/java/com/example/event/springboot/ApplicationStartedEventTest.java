package com.example.event.springboot;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/22 15:20
 * @see org.springframework.context.ApplicationListener 所有子类
 **/
@Component
public class ApplicationStartedEventTest implements ApplicationListener<ApplicationStartedEvent> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void onApplicationEvent(ApplicationStartedEvent event) {
        logger.info("ApplicationStartedEventTest ...., {}", event);
    }
}
