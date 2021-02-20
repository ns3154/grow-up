package com.example.mybatis.service.impl;

import com.example.mybatis.dao.TestMapper;
import com.example.mybatis.domain.Test;
import com.example.mybatis.service.UserService;
import org.springframework.aop.framework.AopContext;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
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
//		insert(counts, pNums);
	    UserServiceImpl userService = (UserServiceImpl) AopContext.currentProxy();
	    userService.insert(counts, pNums);
	    userService.insert1(counts, pNums);
		userService.insert3(counts, pNums);
		userService.insert2(counts, pNums);
	    int i = counts / pNums;

        return test;
    }

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	@Override
	public void insert(int counts, Integer pNums) {
		Test test = new Test();
		test.setCounts(counts+1);
		test.setpNums(pNums+1);
		testMapper.insertSelective(test);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	public void insert1(int counts, int pNums) {
		Test test = new Test();
		test.setCounts(counts+2);
		test.setpNums(pNums+2);
		testMapper.insertSelective(test);
	}

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	private void insert2(int counts, int pNums) {
		Test test = new Test();
		test.setCounts(counts+3);
		test.setpNums(pNums+3);
		testMapper.insert(test);
	}

	@Override
	public void insert3(int counts, int pNums) {
		Test test = new Test();
		test.setCounts(counts+4);
		test.setpNums(pNums+4);
		testMapper.insert(test);
	}

	@Override
    public Test select(Long id) {
        return testMapper.selectByPrimaryKey(id);
    }

	@Transactional(rollbackFor = Exception.class, isolation = Isolation.READ_UNCOMMITTED, propagation = Propagation.REQUIRES_NEW)
	public void isolationRR(int count, int pNums) {
		System.out.println(testMapper.count());
		Test test = new Test();
		test.setCounts(count+1);
		test.setpNums(pNums+1);
		testMapper.insert(test);
	}

	@Override
	@Transactional(rollbackFor = Exception.class, isolation = Isolation.REPEATABLE_READ)
	public Test isolationRU(Integer counts, Integer pNums) {
		Test test = new Test();
		test.setCounts(counts);
		test.setpNums(pNums);
		testMapper.insertSelective(test);
		UserServiceImpl userService = (UserServiceImpl) AopContext.currentProxy();
		userService.isolationRR(counts, pNums);
		System.out.println(testMapper.count());
		return test;
	}
}
