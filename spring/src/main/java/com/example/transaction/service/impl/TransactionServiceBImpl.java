package com.example.transaction.service.impl;


import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import com.example.transaction.service.TransactionServiceB;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/29 15:30
 **/
@Service
public class TransactionServiceBImpl implements TransactionServiceB {

    @Resource
    private TestMapper testMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int update(long id) {
        Test test = new Test();
        test.setId(id);
        test.setCounts(7892);
        int i = testMapper.updateByPrimaryKeySelective(test);
        int z = 1 / 0;
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tranSaction1() {
        try {
            Test test = new Test();
            test.setId(1000L);
            test.setCounts(123);
            testMapper.updateByPrimaryKeySelective(test);
            int b = 1 / 0;
        } catch (Exception e) {
            System.out.println("异常捕获");
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void tranSaction2() {
        Test test = new Test();
        test.setId(1001L);
        test.setCounts(123);
        testMapper.updateByPrimaryKeySelective(test);

        int b = 1 / 0;
    }
}
