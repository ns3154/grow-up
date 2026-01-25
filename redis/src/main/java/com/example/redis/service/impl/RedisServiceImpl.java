package com.example.redis.service.impl;

import com.example.redis.service.RedisService;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisOperations;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.SessionCallback;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 14:23
 **/
@Service
public class RedisServiceImpl implements RedisService {

    @Resource
    RedisTemplate<String, String> redisTemplate;

    @Override
    public Object transactionAnnoTationSetA(String key, String value) {
        return redisTemplate.execute((connection -> {
             connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
             return null;
        }), true);

    }

    @Override
    public Object trancactionAnnoTationSetB(String key, String value) {
        return redisTemplate.execute((connection -> {
            connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
            return null;
        }), true);
    }
}
