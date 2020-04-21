package org.example.excel.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import org.example.excel.domain.CouponPackageCode;

public interface CouponPackageCodeMapper {
    int deleteByPrimaryKey(Long id);

    int insert(CouponPackageCode record);

    int insertSelective(CouponPackageCode record);

    CouponPackageCode selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(CouponPackageCode record);

    int updateByPrimaryKey(CouponPackageCode record);

    int batchInsert(@Param("list") List<CouponPackageCode> list);
}