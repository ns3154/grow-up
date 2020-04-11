package com.example.conditional;

import org.springframework.stereotype.Component;

/**
 * <pre>
 *      Conditional 注解
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/11 11:37
 **/
//@Conditional 自己实现 Condition 接口
//@ConditionalOnBean //当容器中有指定的 Bean 才开启配置。
//@ConditionalOnMissingBean 当容器中没有指定的 Bean 才开启配置。
//@ConditionalOnClass 当容器中有指定的 Class 才开启配置。
//@ConditionalOnMissingClass 当容器中没有指定的 Class 才开启配置。
//@ConditionalOnWebApplication 当前项目类型是 WEB 项目才开启配置。
//@ConditionalOnNotWebApplication 当前项目类型不是 WEB 项目才开启配置。
//@ConditionalOnProperty //当指定的属性有指定的值时才开启配置。
//@ConditionalOnExpression("'${test.user.name}'.equals('杨')") //当 SpEL 表达式为 true 时才开启配置。
//@ConditionalOnJava(JavaVersion.NINE) //当运行的 Java JVM 在指定的版本范围时才开启配置。
//@ConditionalOnResource //当类路径下有指定的资源才开启配置。
//@ConditionalOnJndi 当指定的 JNDI 存在时才开启配置
//@ConditionalOnCloudPlatfCorm  当指定的云平台激活时才开启配置。
//@ConditionalOnSingleCandidate 当指定的 class 在容器中只有一个 Bean，或者同时有多个但为首选时才开启配置。
@Component
public class ConditionalAnnotationTest {

    static {
        System.out.println("ConditionalAnnotationTest 初始化.....");
    }


}
