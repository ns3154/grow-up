package com.example.autowired;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/11/10 15:44
 **/
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class ClassB {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ClassB() {
        logger.info("instantiation.......ClassB");
    }

}
