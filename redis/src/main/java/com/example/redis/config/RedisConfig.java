package com.example.redis.config;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.boot.autoconfigure.jdbc.DataSourceTransactionManagerAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.sql.DataSource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 15:45
 **/
@Configuration
@EnableTransactionManagement
public class RedisConfig {

    /**
     * redistemplate
     * 开启事物 使用 redisTemplate.setEnableTransactionSupport(true)
     * @author 杨帮东
     * @param redisConnectionFactory redisConnectionFactory
     * @since 1.0
     * @date 2020/3/19 18:22
     * @return org.springframework.data.redis.core.RedisTemplate<java.lang.String,java.lang.String>
     * @throws
     */
    @Bean
    public RedisTemplate<String, String> redisTemplate(RedisConnectionFactory redisConnectionFactory) {
        RedisTemplate<String, String> redisTemplate = new RedisTemplate<>();
        redisTemplate.setConnectionFactory(redisConnectionFactory);
        redisTemplate.setKeySerializer(RedisSerializer.string());
        redisTemplate.setValueSerializer(RedisSerializer.string());
        redisTemplate.setHashKeySerializer(RedisSerializer.string());
        redisTemplate.setHashValueSerializer(RedisSerializer.string());
        redisTemplate.setEnableTransactionSupport(true);
        return redisTemplate;
    }

    /**
     * 事物管理器
     * redis 要想实现注解事物,必须要借助jdbc的事物管理器实现
     * @author 杨帮东
     * @param dataSource
     * @since 1.0
     * @date 2020/3/19 17:52
     * @return org.springframework.transaction.PlatformTransactionManager
     * @throws
     */
   @Bean
   public PlatformTransactionManager transactionManager(DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
   }
}
