package com.example.mybatis.service.impl;

import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import com.example.mybatis.service.UserService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import javax.annotation.Resource;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/02 18:38
 **/
@Service
public class UserServiceImpl implements UserService {

    @Value("${test.user.name}")
    private String name;

    @Resource
    private TestMapper testMapper;

    @Transactional(rollbackFor = Exception.class)
    @Override
    public Test create(Integer counts, Integer pNums) {
        System.out.println(name);
        Test test = new Test();
        test.setCounts(counts);
        test.setpNums(pNums);
        testMapper.insertSelective(test);

        int i = counts / pNums;

        return test;
    }



    @Override
    public Test select(Long id) {
        return testMapper.selectByPrimaryKey(id);
    }

}
