package com.example.event.google;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 14:46
 **/
public class OrderCreatedEvent {

    private long orderId;
    private long userId;

    public OrderCreatedEvent(long orderId, long userId) {
        this.setOrderId(orderId);
        this.setUserId(userId);
    }

    public long getOrderId() {
        return orderId;
    }

    public void setOrderId(long orderId) {
        this.orderId = orderId;
    }

    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }
}
