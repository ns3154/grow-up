package org.example.excel.service;

import org.example.excel.domain.CouponPackageCode;

import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 18:00
 **/
public interface ExcelService {

    int create(List<CouponPackageCode> list);
}
