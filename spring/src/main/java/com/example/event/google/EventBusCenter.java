package com.example.event.google;

import com.example.annotation.EventBusListener;
import com.google.common.eventbus.AsyncEventBus;
import com.google.common.eventbus.EventBus;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.Executors;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 14:38
 **/
@Component
public class EventBusCenter implements CommandLineRunner {

    static {
        System.out.println("EventBusCenter实例化....");
    }

    @Resource
    private ConfigurableListableBeanFactory beanFactory;

    /**
     * 管理同步事件
     */
    private final EventBus syncEventBus = new EventBus();

    /**
     * 管理异步事件
     */
    private final AsyncEventBus asyncEventBus = new AsyncEventBus(Executors.newCachedThreadPool());

    public void postSync(Object event) {
        syncEventBus.post(event);
    }

    public void postAsync(Object event) {
        asyncEventBus.post(event);
    }

    @Override
    public void run(String... args) throws Exception {
        System.out.println("EventBusCenter CommandLineRunner 回调....");
    }

    @PostConstruct
    public void init() {

        // 获取所有带有 @EventBusListener 的 bean，将他们注册为监听者
        Map<String, Object> beansWithAnnotation = beanFactory.getBeansWithAnnotation(EventBusListener.class);

        beansWithAnnotation.entrySet().forEach(s -> {
            asyncEventBus.register(s.getValue());
            asyncEventBus.register(s.getValue());
        });

    }
}
