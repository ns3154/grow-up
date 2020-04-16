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
public class BFilter implements Filter{

    @Override
    public Object invoker(InvokerService invokerService, Map<String, String> params) {
        System.out.println("执行" + filterName() + "............");
        params.put(order() + "", filterName());
        return invokerService.invoker(params);
    }

    @Override
    public int order() {
        return 2;
    }

    @Override
    public String filterName() {
        return "B";
    }
}
