package com.example.demo;

import org.springframework.core.ResolvableType;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/21 10:47
 **/
public class ResolvableTypeDemo {


    public static void main(String[] args) {
        ResolvableType resolvableType = ResolvableType.forClass(List.class);
    }
}
