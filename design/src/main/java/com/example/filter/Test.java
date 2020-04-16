package com.example.filter;

import java.util.*;

/**
 * <pre>
 *      学习duboo 实际上所有filter都是动态加载的
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/16 10:53
 **/
public class Test {

    private static InvokerService buildInvokerChain(InvokerService invokerService) {
        InvokerService last = invokerService;
        Filter a = new AFilter();
        Filter b = new BFilter();
        Filter c = new CFilter();

        List<Filter> list = new ArrayList<>();
        list.add(c);
        list.add(b);
        list.add(a);
        list.sort(Comparator.comparingInt(Filter::order));

        for (int i = 0, size = list.size();i < size;i++) {
            Filter filter = list.get(i);
            InvokerService next = last;

//            last = params -> filter.invoker(next, params);
            last = new InvokerService() {
                @Override
                public Object invoker(Map<String, String> params) {
                    return filter.invoker(next, params);
                }
            };

        }
        return last;
    }

    public static void main(String[] args) {
        Map<String, String> param = new LinkedHashMap<>();
        param.put("start", "main");
        InvokerService invokerService = buildInvokerChain(new InvokerServiceImpl());
        System.out.println(invokerService.invoker(param));
    }
}
