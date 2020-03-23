package com.example.mvc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * <pre>
 *      入参/出参 加解密
 * </pre>
 * @author 杨帮东 (qq:397827222)
 * @since 1.0
 * @date 2019/05/08 10:24
 **/
@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface Secret {

    /**
     * 入参 默认解密
     * @return
     */
    boolean decrypt() default true;

    /**
     * 出参 默认加密
     * @return
     */
    boolean encrypt() default true;
}
