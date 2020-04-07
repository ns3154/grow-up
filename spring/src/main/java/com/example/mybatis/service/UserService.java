package com.example.mybatis.service;

import com.example.mybatis.domain.Test;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/02 18:38
 **/
public interface UserService {

    /**
     * 创建数据
     * @param counts counts
     * @param pNums pNums
     * @return
     */
    Test create(Integer counts, Integer pNums);
}
