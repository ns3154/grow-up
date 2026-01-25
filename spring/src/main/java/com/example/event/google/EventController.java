package com.example.event.google;

import com.example.model.ModelMessge;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import jakarta.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 14:49
 **/
@RestController
@RequestMapping("event")
public class EventController {

    @Resource
    private EventBusCenter eventBusCenter;

    @GetMapping("createOrder")
    public ModelMessge<String> createOrder(Long userId, Long orderId) {
        eventBusCenter.postAsync(new OrderCreatedEvent(orderId, userId));
        return new ModelMessge<String>().ok("ok");
    }

    @GetMapping("modifyOrder")
    public ModelMessge<String> modifyOrder(Long userId, Long orderId) {
        eventBusCenter.postAsync(new OrderModifyEvent(orderId, userId));
        return new ModelMessge<String>().ok("ok");
    }
}
