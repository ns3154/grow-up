package com.example.demo.dsl;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:58
 **/
public class TradeBuilderWithStock {

    private final MethodChainingOrderBuidler methodChainingOrderBuidler;

    private final Trade trade;

    public TradeBuilderWithStock(MethodChainingOrderBuidler methodChainingOrderBuidler, Trade trade) {
        this.methodChainingOrderBuidler = methodChainingOrderBuidler;
        this.trade = trade;
    }

    public MethodChainingOrderBuidler at(double price) {
        trade.setPrice(price);
        return methodChainingOrderBuidler.addTrade(trade);
    }
}
