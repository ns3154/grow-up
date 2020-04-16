package com.example.filter;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 10:51
 **/
public class AFilter implements Filter {

    @Override
    public Object invoker(InvokerService invokerService, Map<String, String> params) {
        System.out.println("执行" + filterName() + "............");
        params.put(order() + "", filterName());
        return invokerService.invoker(params);
    }

    @Override
    public int order() {
        return 0;
    }

    @Override
    public String filterName() {
        return "A";
    }
}
