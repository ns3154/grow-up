package com.example.decorator;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 18:17
 **/
public class Lemon extends Condiment {

    private Beverage bevarage;

    /**
     * 这里很关键，需要传入具体的饮料，如需要传入没有被装饰的红茶或绿茶，
     * 当然也可以传入已经装饰好的芒果绿茶，这样可以做芒果柠檬绿茶
     * @param bevarage
     */
    public Lemon(Beverage bevarage) {
        this.bevarage = bevarage;
    }
    @Override
    public String getDescription() {
        // 装饰
        return bevarage.getDescription() + ", 加柠檬";
    }
    @Override
    public double cost() {
        // 装饰 加柠檬需要 2 元
        return bevarage.cost() + 2;
    }

}
