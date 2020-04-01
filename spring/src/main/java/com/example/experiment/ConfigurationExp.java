package com.example.experiment;

import com.example.model.bean.Order;
import com.example.model.bean.User;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/31 16:29
 **/
@Configuration
public class ConfigurationExp {


    @Bean
    public User user() {
        return User.newBuilder().withId(1L).withUserName("杨").withAge(2).build();
    }

    @Bean
    public Order order() {
        return Order.newBuilder().withOrderId(1L).withOrderNo("1234").withUserId(2L).build();
    }

}
