package com.example.bridge;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 17:55
 **/
public class Rectangle extends Shape {
    private int x;
    private int y;

    public Rectangle(int x, int y, DrawAPI drawApi) {
        super(drawApi);
        this.x = x;
        this.y = y;
    }

    @Override
    public void draw() {
        drawAPI.draw(0, x, y);
    }
}
