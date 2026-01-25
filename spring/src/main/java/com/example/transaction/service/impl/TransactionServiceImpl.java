package com.example.transaction.service.impl;

import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import com.example.transaction.service.TransactionService;
import com.example.transaction.service.TransactionServiceB;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import jakarta.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/29 15:21
 **/
@Service
public class TransactionServiceImpl implements TransactionService {

    @Resource
    private TestMapper testMapper;

    @Resource
    private TransactionServiceB transactionServiceB;

    @Override
    public int create() {
        Test test = new Test();
        test.setCounts(0);
        test.setpNums(0);
        int i = testMapper.insertSelective(test);
        return i;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createWithSimpleTransaction() {
        Test test = new Test();
        test.setCounts(0);
        test.setpNums(0);
        int i = testMapper.insertSelective(test);
        int t = 1 / 0;
        return i;
    }

    /**
     * 会回滚 transactionServiceB.update(1012); 已被标记回滚了
     * @author 杨帮东
     * @param
     * @since 1.0
     * @date 2020/6/15 16:54
     * @return int
     * @throws
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public int createWithTransaction() {
        Test test = new Test();
        test.setCounts(0);
        test.setpNums(0);
        int i = testMapper.insertSelective(test);
        try {
            transactionServiceB.update(1012);
        } catch (Exception e) {
            System.out.println("catch住了");
        }
        return i;
    }

    /**
     * 都会回滚
     * @return
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public String tranSaction() {
        transactionServiceB.tranSaction1();
        transactionServiceB.tranSaction2();

        return "ok";
    }

}
