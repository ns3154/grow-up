package com.example.enable;

import org.springframework.beans.factory.annotation.AnnotatedGenericBeanDefinition;
import org.springframework.beans.factory.support.BeanDefinitionReaderUtils;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.context.annotation.ImportBeanDefinitionRegistrar;
import org.springframework.core.type.AnnotationMetadata;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/24 11:09
 **/
public class ImportBeanDefinitionRegistrarTest implements ImportBeanDefinitionRegistrar {

    @Override
    public void registerBeanDefinitions(AnnotationMetadata importingClassMetadata, BeanDefinitionRegistry registry) {
        AnnotatedGenericBeanDefinition annotatedGenericBeanDefinition =
                new AnnotatedGenericBeanDefinition(BeanDefinitionRegistrarTest.class);
//        registry.registerBeanDefinition("beanDefinitionRegistrarTest", annotatedGenericBeanDefinition); 二选一即可
        BeanDefinitionReaderUtils.registerWithGeneratedName(annotatedGenericBeanDefinition, registry);
    }
}
