package com.example.demo.dsl;

/**
 * <pre>
 *      股票类
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:02
 **/
public class Stock {

    private String symbol;

    private String market;

    public String getSymbol() {
        return symbol;
    }

    public void setSymbol(String symbol) {
        this.symbol = symbol;
    }

    public String getMarket() {
        return market;
    }

    public void setMarket(String market) {
        this.market = market;
    }
}
