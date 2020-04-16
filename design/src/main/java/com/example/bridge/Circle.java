package com.example.bridge;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 17:54
 **/
public class Circle extends Shape {
    private int radius;

    public Circle(int radius, DrawAPI drawApi) {
        super(drawApi);
        this.radius = radius;
    }


    @Override
    public void draw() {
        drawAPI.draw(radius, 0, 0);
    }
}
