package com.example.annotation;

import java.lang.annotation.*;

/**
 * <pre>
 *      只是个标识 与 ConfigurationProperties(prefix) 配合使用
 *      使用此注解动态配置要实现get set
 *      支持数据类型为:
 *          Integer   int
 *          Long      long
 *          Double    double
 *          Boolean   boolean
 *          String
 *          Set<Integer int,Long,long,Double,double,Boolean,boolean,String>
 *          List<Integer int,Long,long,Double,double,Boolean,boolean,String>
 *       如有需求在增加
 *       不支持继承关系,集合嵌套关系
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2019/11/06 11:47
 **/
@Target(ElementType.TYPE)
@Retention(RetentionPolicy.RUNTIME)
@Documented
public @interface ApolloRefreshScope {
}
