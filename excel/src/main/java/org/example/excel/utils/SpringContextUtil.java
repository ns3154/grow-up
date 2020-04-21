package org.example.excel.utils;

import org.example.excel.service.ExcelService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 18:07
 **/
@Component
public class SpringContextUtil implements BeanFactoryAware {

    private static BeanFactory beanFactory;
    @Override
    public void setBeanFactory(BeanFactory beanFactory) throws BeansException {
        SpringContextUtil.beanFactory = beanFactory;
    }

    public static ExcelService getBean() {
        return beanFactory.getBean(ExcelService.class);
    }
}
