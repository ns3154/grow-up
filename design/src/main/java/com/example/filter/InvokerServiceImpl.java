package com.example.filter;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 10:52
 **/
public class InvokerServiceImpl implements InvokerService {
    @Override
    public Object invoker(Map<String, String> params) {
        System.out.println("执行InvokerServiceImpl............");
        params.put("END", "InvokerServiceImpl");
        return params;
    }
}
