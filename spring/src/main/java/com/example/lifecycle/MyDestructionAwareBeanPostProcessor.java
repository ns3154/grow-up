package com.example.lifecycle;

import com.example.model.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.DestructionAwareBeanPostProcessor;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *      bean 销毁
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/07 19:30
 **/
@Component
public class MyDestructionAwareBeanPostProcessor implements DestructionAwareBeanPostProcessor {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public void postProcessBeforeDestruction(Object bean, String beanName) throws BeansException {
        logger.info("** {}:销毁前***", beanName);
    }

    @Override
    public boolean requiresDestruction(Object bean) {
        return true;
    }
}
