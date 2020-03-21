package com.example.proxy.demo.service.base;

import com.example.proxy.demo.Storage;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:59
 **/
public class AbstractUser {

    public boolean delUser(Long userId) {
        return Storage.delUser(userId);
    }
}
