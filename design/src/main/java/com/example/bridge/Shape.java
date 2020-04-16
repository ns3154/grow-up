package com.example.bridge;

/**
 * <pre>
 *      形状抽象类
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 17:53
 **/
public abstract class Shape {

    protected DrawAPI drawAPI;

    protected Shape(DrawAPI drawAPI) {
        this.drawAPI = drawAPI;
    }

    public abstract void draw();
}
