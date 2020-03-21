package com.example.proxy.demo;

import com.example.proxy.demo.model.UserPO;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:03
 **/
public class Storage {

    private Storage() {
        // nothing
    }

    private static final Map<Long, UserPO> cache = new ConcurrentHashMap<>();

    public static UserPO getUserById(Long userId) {
        return cache.get(userId);
    }

    public static boolean crateUser(UserPO user) {
        return null == cache.putIfAbsent(user.getId(), user);
    }

    public static boolean delUser(Long userId) {
        return null != cache.remove(userId);
    }
}
