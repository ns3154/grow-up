package com.example.redis.controller;

import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/03/18 17:43
 **/
@RestController
@RequestMapping("redis")
public class RedisController {

    @Resource
    private StringRedisTemplate redisTemplate;

    @GetMapping("set")
    public Object set(String key, String value) {
        Boolean b = redisTemplate.execute((RedisCallback<Boolean>) connection ->
                connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8)));
        return b;
    }


    @GetMapping("get")
    public Object get(String key) {
        String s = redisTemplate.execute(new RedisCallback<String>() {
                        @Override
                        public String doInRedis(RedisConnection connection) throws DataAccessException {
                            byte[] bytes = connection.get(key.getBytes(StandardCharsets.UTF_8));

                            if (null != bytes) {
                                return new String(bytes, StandardCharsets.UTF_8);
                            }
                            return null;
                        }
                    });

        return s;
    }


}
