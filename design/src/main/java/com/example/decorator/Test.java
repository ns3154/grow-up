package com.example.decorator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 18:19
 **/
public class Test {

    public static void main(String[] args) {
        // 首先，我们需要一个基础饮料，红茶、绿茶或咖啡
        Beverage beverage = new GreenTea();
        // 开始装饰
        beverage = new Lemon(beverage); // 先加一份柠檬
        beverage = new Mongo(beverage); // 再加一份芒果

        Lemon lemon = new Lemon(new Mongo(new GreenTea()));

        System.out.println(beverage.getDescription() + " 价格：￥" + beverage.cost());
        System.out.println(lemon.getDescription() + " 价格：￥" + lemon.cost());
    }
}
