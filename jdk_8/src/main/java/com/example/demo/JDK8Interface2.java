package com.example.demo;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 19:02
 **/
public interface JDK8Interface2 {

    //接口中可以定义静态方法了
    public static void staticMethod(){
        System.out.println("接口中的静态方法");
    }
    //使用default之后就可以定义普通方法的方法体了
    public default void defaultMethod(){
        System.out.println("接口中的默认方法");
    }
}
