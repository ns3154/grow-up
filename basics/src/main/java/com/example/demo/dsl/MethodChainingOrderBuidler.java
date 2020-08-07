package com.example.demo.dsl;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:49
 **/
public class MethodChainingOrderBuidler {

    public final Order order = new Order();

    private MethodChainingOrderBuidler(String customer) {
        order.setCustomer(customer);
    }


    public static MethodChainingOrderBuidler forCusomer(String customer) {
        return new MethodChainingOrderBuidler(customer);
    }

    public TradeBuilder buy(int quantity) {
        return new TradeBuilder(this, Trade.Type.BUY, quantity);
    }

    public TradeBuilder sell(int quantity) {
        return new TradeBuilder(this, Trade.Type.SELL, quantity);
    }

    public MethodChainingOrderBuidler addTrade(Trade trade) {
        order.addTrade(trade);
        return this;
    }

    public Order end() {
        return order;
    }
}
