package com.example.bridge;

/**
 * <pre>
 *      理解桥梁模式，其实就是理解代码抽象和解耦。
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 17:51
 **/
public interface DrawAPI {

    public void draw(int radius, int x, int y);
}
