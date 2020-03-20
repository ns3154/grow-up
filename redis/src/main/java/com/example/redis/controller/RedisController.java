package com.example.redis.controller;

import com.example.redis.service.RedisService;
import com.example.redis.service.TestService;
import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import javax.annotation.Resource;
import java.nio.charset.StandardCharsets;
import java.util.List;

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
    private RedisTemplate<String, String> redisTemplate;

    @Resource
    private TestService testService;

    @GetMapping("set")
    public Object set(String key, String value) {
        return redisTemplate.execute(connection ->
                connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8)),
                true);
    }


    @GetMapping("get")
    public Object get(String key) {
        return redisTemplate.execute(connection -> {
            byte[] bytes = connection.get(key.getBytes(StandardCharsets.UTF_8));

            if (null != bytes) {
                return new String(bytes, StandardCharsets.UTF_8);
            }
            return null;
        }, true);
    }

    @GetMapping("transaction")
    public Object transaction(String key, String value) {
        List<Object> object = redisTemplate.execute(connection -> {
            connection.watch(key.getBytes(StandardCharsets.UTF_8));
            connection.multi();
            connection.set("abc".getBytes(StandardCharsets.UTF_8), "sdfs".getBytes(StandardCharsets.UTF_8));
            connection.set(key.getBytes(StandardCharsets.UTF_8), value.getBytes(StandardCharsets.UTF_8));
            connection.get(key.getBytes(StandardCharsets.UTF_8));
            return connection.exec();
        }, true);

        return object;
    }

    @GetMapping("transcantionAnnotionTest")
    public Object transcantionAnnotionTest(String key, String value, String key1, String value1, Integer e) {
        return testService.redisTranscantionTest(key, value, key1, value1, e);
    }



}
