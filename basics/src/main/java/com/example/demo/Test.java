package com.example.demo;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.ConcurrentHashMap;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/17 14:01
 **/
public class Test {

    private static final Logger logger = LoggerFactory.getLogger(Test.class);

    public static void main(String[] args) {

        try {
            int i = 1/0;
        } catch (Exception e) {
            logger.error("{},{}", "1", e.getMessage(), e);
        }



        ConcurrentHashMap<String, String> map = new ConcurrentHashMap<>();
        map.put("sss", "ddd");
    }

    private static int test() {
        int z = 0;
        try {
            z++;
            return z;
        } finally {
            z++;
            System.out.println("finally" + z);
        }
    }

    private static Integer test1() {
        Integer z = new Integer(1);
        try {
            return z;
        } finally {
            z = 3;
            System.out.println("finally" + z);
        }
    }

    private static Model test2() {
        Model model = new Model();
        try {
            model.setCode(10);
            return model;
        } finally {
            model.setCode(11);
        }
    }

    @org.junit.jupiter.api.Test
    public void sss() {
        int t = 3;
        int z = 5;
        System.out.println(t & z);

    }
}
