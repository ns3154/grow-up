package com.example.annotation.autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/10 17:09
 **/
@Component
public class ClassD {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ClassD() {
        logger.info("ClassD instantiation.............");
    }

    public void out() {
        logger.error("call ClassD.out()..................");
    }
}
