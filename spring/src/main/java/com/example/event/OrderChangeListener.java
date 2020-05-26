package com.example.event;

import com.example.annotation.EventBusListener;
import com.google.common.eventbus.Subscribe;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 14:46
 **/
@Component
@EventBusListener
public class OrderChangeListener {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Subscribe
    public void created(OrderCreatedEvent event) {
        long orderId = event.getOrderId();
        long userId = event.getUserId();
        logger.error("*** 创建订单事件,userid:{}, orderId:{}***", userId, orderId);
        // 订单创建成功后的各种操作，如发短信、发邮件等等。
        // 注意，事件可以被订阅多次，也就是说可以有很多方法监听 OrderCreatedEvent 事件，
        // 所以没必要在一个方法中处理发短信、发邮件、更新库存等
    }

    @Subscribe
    public void created1(OrderCreatedEvent event) {
        long orderId = event.getOrderId();
        long userId = event.getUserId();
        logger.error("*** 创建订单事件1,userid:{}, orderId:{}***", userId, orderId);
        // 订单创建成功后的各种操作，如发短信、发邮件等等。
        // 注意，事件可以被订阅多次，也就是说可以有很多方法监听 OrderCreatedEvent 事件，
        // 所以没必要在一个方法中处理发短信、发邮件、更新库存等
    }

    @Subscribe
    public void modify(OrderModifyEvent event) {
        long orderId = event.getOrderId();
        long userId = event.getUserId();
        logger.error("*** 修改订单事件,userid:{}, orderId:{}***", userId, orderId);
        // 订单创建成功后的各种操作，如发短信、发邮件等等。
        // 注意，事件可以被订阅多次，也就是说可以有很多方法监听 OrderCreatedEvent 事件，
        // 所以没必要在一个方法中处理发短信、发邮件、更新库存等
    }

//    @Subscribe
//    public void change(OrderChangeEvent event) {
//        // 处理订单变化后的修改
//        // 如发送提醒、更新物流等
//    }
}
