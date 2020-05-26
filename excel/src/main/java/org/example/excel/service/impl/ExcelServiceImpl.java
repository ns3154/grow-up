package org.example.excel.service.impl;

import org.example.excel.dao.CouponPackageCodeMapper;
import org.example.excel.domain.CouponPackageCode;
import org.example.excel.service.ExcelService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 18:01
 **/
@Service
public class ExcelServiceImpl implements ExcelService {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private CouponPackageCodeMapper couponPackageCodeMapper;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public int create(List<CouponPackageCode> list) {
        int size = list.size();
        List<CouponPackageCode> l = new ArrayList<>(1000);
        int num = 0;
        for (int i = 0;i < size;i++) {
            l.add(list.get(i));
            if (l.size() >= 3000) {
                int j = couponPackageCodeMapper.batchInsert(l);
                num += j;
                l.clear();
                logger.error("执行分页插入,当前插入:{}, 总插入:{}, 总共需要插入:{}", j, num, size);
            }

            if (i == size -1 && !l.isEmpty()) {
                int j = couponPackageCodeMapper.batchInsert(l);
                num += j;
                logger.error("执行分页插入,当前插入:{}, 总插入:{}, 总共需要插入:{}", j, num, size);
            }
        }
        return num;
    }
}
