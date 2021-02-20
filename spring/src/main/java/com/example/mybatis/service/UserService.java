package com.example.mybatis.service;

import com.example.mybatis.domain.Test;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

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

    /**
     * 查询用户
     * @param id 根据id
     * @return
     */
    Test select(Long id);

    void insert(int counts, Integer pNums);

	@Transactional(rollbackFor = Exception.class, propagation = Propagation.REQUIRES_NEW)
	void insert3(int counts, int pNums);

	Test isolationRU(Integer counts, Integer pNums);
}
