package com.example.redis.service;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 15:59
 **/
public interface TestService {

    Object redisTranscantionTest(String key, String value, String key1, String value1, Integer e);
}
