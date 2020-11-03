package com.example.enable;

import org.springframework.context.annotation.Import;

import java.lang.annotation.*;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/24 10:59
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
@Import({EnableImpotTest.class, MyImportSelector.class, ImportBeanDefinitionRegistrarTest.class})
public @interface MyEnable {
}
