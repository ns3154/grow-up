package com.example.async.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 *      自定义异步注解
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 16:11
 **/
@Target({ElementType.TYPE, ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface MyAsync {

    String value() default "";
}
