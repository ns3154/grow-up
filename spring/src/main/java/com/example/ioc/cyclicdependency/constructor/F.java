package com.example.ioc.cyclicdependency.constructor;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *      构造器注入 循环依赖 spring 无法解决
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/09 15:03
 **/
@Component
@ConditionalOnProperty(prefix = "test", name = "constructor", havingValue = "true")
public class F {

    private D d;

    private String name;

    public F(D d) {
        this.d = d;
        this.name = "F";
    }
}
