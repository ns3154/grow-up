package com.example.filter;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 10:50
 **/
public interface Filter {

    public Object invoker(InvokerService invokerService, Map<String, String> params);

    int order();

    String filterName();
}
