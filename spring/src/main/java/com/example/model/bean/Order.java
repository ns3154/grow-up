package com.example.model.bean;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 16:54
 **/
public class Order {

    private Long orderId;

    private String orderNo;

    private Long userId;

    private Order(Builder builder) {
        setOrderId(builder.orderId);
        setOrderNo(builder.orderNo);
        setUserId(builder.userId);
    }

    public static Builder newBuilder() {
        return new Builder();
    }

    public static Builder newBuilder(Order copy) {
        Builder builder = new Builder();
        builder.orderId = copy.getOrderId();
        builder.orderNo = copy.getOrderNo();
        builder.userId = copy.getUserId();
        return builder;
    }


    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    /**
     * {@code Order} builder static inner class.
     */
    @SuppressWarnings("PMD.AvoidFieldNameMatchingMethodName")
    public static final class Builder {
        private Long orderId;
        private String orderNo;
        private Long userId;

        private Builder() {
        }

        /**
         * Sets the {@code orderId} and returns a reference to this Builder so that the methods can be chained together.
         * @param orderId the {@code orderId} to set
         * @return a reference to this Builder
         */
        public Builder withOrderId(Long orderId) {
            this.orderId = orderId;
            return this;
        }

        /**
         * Sets the {@code orderNo} and returns a reference to this Builder so that the methods can be chained together.
         * @param orderNo the {@code orderNo} to set
         * @return a reference to this Builder
         */
        public Builder withOrderNo(String orderNo) {
            this.orderNo = orderNo;
            return this;
        }

        /**
         * Sets the {@code userId} and returns a reference to this Builder so that the methods can be chained together.
         * @param userId the {@code userId} to set
         * @return a reference to this Builder
         */
        public Builder withUserId(Long userId) {
            this.userId = userId;
            return this;
        }

        /**
         * Returns a {@code Order} built from the parameters previously set.
         *
         * @return a {@code Order} built with parameters of this {@code Order.Builder}
         */
        public Order build() {
            return new Order(this);
        }
    }
}
