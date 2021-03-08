package com.example.mybatis.service;

import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/03/03 11:25
 **/
public class TestService {

    @Resource
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
    public int create(Integer counts, Integer pNums) {
        Test test = new Test();
        test.setCounts(counts + 5);
        test.setpNums(pNums + 5);
        return testMapper.insertSelective(test);
    }
}
