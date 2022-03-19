package com.example.demo.lambda;

import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/02/13 00:13
 **/
public class Abc {

    public static void main(String[] args) {
        Stream.of("bei", "sd", "sdfsf", "sdf")
                .map(String::length)
                .filter(s -> s > 2)
                .collect(Collectors.toList());
    }
}
