package com.example.factory.simple;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 17:16
 **/
public class Test {

    public static void main(String[] args) {
        Food food = FoodFactory.getFood("huangmenji");
        assert food != null;
        food.eat();
    }
}
