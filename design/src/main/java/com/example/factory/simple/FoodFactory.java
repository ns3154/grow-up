package com.example.factory.simple;

import java.math.BigDecimal;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/15 17:00
 **/
public class FoodFactory {


    public static Food getFood(String name) {
        if ("lanzhoulanmian".equals(name)) {
            Food lanZhouLaMian = new LanZhouLaMian();
            lanZhouLaMian.setFrom("兰州");
            lanZhouLaMian.setPrice(new BigDecimal("23.6"));
            lanZhouLaMian.setSize(3);
            return lanZhouLaMian;
        } else if ("huangmenji".equals(name)) {
            Food huangmenji = new HuangMenJi();
            huangmenji.setPrice(new BigDecimal("30"));
            huangmenji.setSize(2);
            huangmenji.setFrom("沙县小吃");
            return huangmenji;
        }
        return null;
    }

}
