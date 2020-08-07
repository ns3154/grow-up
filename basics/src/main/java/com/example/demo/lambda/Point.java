package com.example.demo.lambda;

import java.util.Comparator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 15:50
 **/
public class Point {

    public static final Comparator<Point> COMPARATOR =
            Comparator.comparing(Point::getX).thenComparing(Point::getY);

    private  int x;

    private  int y;

    public Point(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public Point moveX(int move) {
        return new Point(this.x + move, y);
    }


}
