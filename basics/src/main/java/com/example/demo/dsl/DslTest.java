package com.example.demo.dsl;

import org.junit.Test;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:07
 **/
public class DslTest {

    @Test
    public void firstVersion() {
        Order order = new Order();
        order.setCustomer("BigBank");

        Trade trade1 = new Trade();
        trade1.setType(Trade.Type.BUY);

        Stock stock1 = new Stock();
        stock1.setSymbol("IBM");
        stock1.setMarket("NYSE");

        trade1.setStock(stock1);
        trade1.setPrice(125.00);
        trade1.setQuantity(80);

        order.addTrade(trade1);

        Trade trade2 = new Trade();
        trade2.setType(Trade.Type.SELL);

        Stock stock2 = new Stock();
        stock2.setSymbol("GOOGLE");
        stock2.setMarket("NASDAQ");

        trade2.setStock(stock2);
        trade2.setPrice(375.00);
        trade2.setQuantity(50);

        order.addTrade(trade2);

        System.out.println(order.getCustomer());
    }

    @Test
    public void methodChaining() {
        Order order = MethodChainingOrderBuidler
                .forCusomer("BigBank")
                    .buy(80)
                    .stock("IBM")
                        .on("NYSE")
                    .at(125.00)
                    .sell(50)
                    .stock("GOOGLE")
                        .on("NASDAQ")
                    .at(375.00)
                .end();

        System.out.println(order.getCustomer());
    }
}
