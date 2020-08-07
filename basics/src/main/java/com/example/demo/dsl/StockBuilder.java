package com.example.demo.dsl;

import java.util.logging.SocketHandler;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:55
 **/
public class StockBuilder {

    private final MethodChainingOrderBuidler methodChainingOrderBuidler;

    private final Trade trade;

    private final Stock stock = new Stock();

    public StockBuilder(MethodChainingOrderBuidler methodChainingOrderBuidler, Trade trade, String symbol) {
        this.methodChainingOrderBuidler = methodChainingOrderBuidler;
        this.trade = trade;
        stock.setSymbol(symbol);
    }

    public TradeBuilderWithStock on(String market) {
        stock.setMarket(market);
        trade.setStock(stock);
        return new TradeBuilderWithStock(methodChainingOrderBuidler, trade);
    }

}
