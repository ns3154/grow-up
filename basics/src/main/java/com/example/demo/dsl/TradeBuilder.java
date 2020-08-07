package com.example.demo.dsl;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:52
 **/
public class TradeBuilder {

    private MethodChainingOrderBuidler methodChainingOrderBuidler;

    public final Trade trade = new Trade();

    public TradeBuilder(MethodChainingOrderBuidler methodChainingOrderBuidler, Trade.Type type, int quantity) {
        this.methodChainingOrderBuidler = methodChainingOrderBuidler;
        trade.setType(type);
        trade.setQuantity(quantity);
    }

    public StockBuilder stock(String symbol) {
        return new StockBuilder(methodChainingOrderBuidler, trade, symbol);
    }
}
