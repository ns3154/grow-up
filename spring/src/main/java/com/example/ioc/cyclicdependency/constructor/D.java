package com.example.ioc.cyclicdependency.constructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *      构造器注入 循环依赖 spring 无法解决
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/09 15:02
 **/
@Component
@ConditionalOnProperty(prefix = "test", name = "constructor", havingValue = "true")
public class D {

    private E e;

    private String name;

    public D(E e) {
        this.e = e;
        this.name = "D";
    }
}
