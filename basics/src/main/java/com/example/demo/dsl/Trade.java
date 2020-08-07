package com.example.demo.dsl;

/**
 * <pre>
 *      交易
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/08/07 19:03
 **/
public class Trade {

    public enum Type { BUY, SELL }

    private Type type;

    private Stock stock;

    private int quantity;

    private double price;

    public Type getType() {
        return type;
    }

    public void setType(Type type) {
        this.type = type;
    }

    public Stock getStock() {
        return stock;
    }

    public void setStock(Stock stock) {
        this.stock = stock;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
