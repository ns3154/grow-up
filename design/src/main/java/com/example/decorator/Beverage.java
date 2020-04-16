package com.example.decorator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 18:15
 **/
public abstract class Beverage {

    /**
     * 返回描述
     * @return String
     */
    public abstract String getDescription();

    /**
     * 返回价格
     * @return double
     */
    public abstract double cost();
}
