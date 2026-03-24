package com.example.proxy.demo.service.impl;

import com.example.proxy.demo.Storage;
import com.example.proxy.demo.service.UserService;
import com.example.proxy.demo.model.UserPO;
import com.example.proxy.demo.service.base.AbstractUser;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:02
 **/
public class UserServiceImpl extends AbstractUser implements UserService {

    @Override
    public boolean addUser(UserPO user) {
        return Storage.crateUser(user);
    }

    @Override
    public UserPO getUserById(Long id) {
        return Storage.getUserById(id);
    }
}
