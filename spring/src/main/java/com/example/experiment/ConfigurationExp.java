package com.example.experiment;

import com.example.model.bean.Order;
import com.example.model.bean.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Bean
    public User user() {
        logger.error("******** @Bean User 执行 *********");
        return User.newBuilder().withId(1L).withUserName("杨").withAge(2).build();
    }



}
