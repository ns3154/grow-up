package com.example.mvc.config.bean;

import lombok.extern.slf4j.Slf4j;
import org.springframework.context.Lifecycle;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class Life implements Lifecycle {

    /**
     *
     */
    @Override public void start() {
        log.info("start");
    }

    /**
     *
     */
    @Override public void stop() {
        log.info("stop");
    }

    /**
     * @return
     */
    @Override public boolean isRunning() {
        log.info("running");
        return true;
    }
}
