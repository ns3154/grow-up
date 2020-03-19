package com.example.redis.service;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/19 14:23
 **/
public interface RedisService {

    Object transactionAnnoTationSetA(String key, String value);

    Object trancactionAnnoTationSetB(String key, String value);
}
