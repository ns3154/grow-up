package com.example.demo.statics;

import java.util.HashSet;
import java.util.Map;
import java.util.Set;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/21 16:48
 **/
public class Demo {

    private static Set<String> set = new HashSet<>();

    private static Integer integer = 1;

    public void modify(String s, Integer integer) {
        set.add(s);
        integer = integer;
    }

    public Set<String> getSet() {
        return set;
    }

    public Integer getInteger() {
        return integer;
    }
}
