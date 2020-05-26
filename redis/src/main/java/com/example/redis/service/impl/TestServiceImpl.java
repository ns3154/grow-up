package com.example.redis.service.impl;

import com.example.redis.service.RedisService;
import com.example.redis.service.TestService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 15:59
 **/
@Service
public class TestServiceImpl implements TestService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisService redisService;

    @Resource
    private RedisTemplate<String, String> redisTemplate;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public Object redisTranscantionTest(String key, String value, String key1, String value1, Integer e) {
        List<Object> list = new ArrayList<>();
        Object aBoolean = redisService.transactionAnnoTationSetA(key, value);
        String abc = redisTemplate.opsForValue().get("abc");
        redisTemplate.opsForValue().set(key + key1, value + value1);
        logger.error("** 获取 value:{}", abc);
        Object aBoolean1 = redisService.trancactionAnnoTationSetB(key1, value1);
        list.add(aBoolean);
        list.add(aBoolean1);
        int i = 1 / e;
        return list;
    }
}
