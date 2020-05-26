package com.example.lifecycle;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/05/26 10:50
 **/
@Component
public class MyInitializingBean implements InitializingBean {

    private final static Logger logger = LoggerFactory.getLogger(MyInitializingBean.class);

    static {
        logger.error("**** MyInitializingBean 实例化****");
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        logger.error("**** MyInitializingBean InitializingBean.afterPropertiesSet 回调 ****");
    }
}
