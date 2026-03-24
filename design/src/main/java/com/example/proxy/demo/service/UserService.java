package com.example.proxy.demo.service;

import com.example.proxy.demo.model.UserPO;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/03/21 14:01
 **/
public interface UserService {

    boolean addUser(UserPO user);

    UserPO getUserById(Long id);
}
