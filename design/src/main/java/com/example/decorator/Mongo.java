package com.example.decorator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 18:18
 **/
public class Mongo extends Condiment {

    private Beverage bevarage;

    public Mongo(Beverage bevarage) {
        this.bevarage = bevarage;
    }
    @Override
    public String getDescription() {
        return bevarage.getDescription() + ", 加芒果";
    }
    @Override
    public double cost() {
        // 加芒果需要 3 元
        return bevarage.cost() + 3;
    }
}
