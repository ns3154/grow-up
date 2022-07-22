package com.leet.code.com.leet.code;

import org.junit.jupiter.api.Test;

import java.util.*;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/21 22:32
 **/
public class Ms {

    static Map<Integer, String> map = new HashMap<>();
    static List<Integer> list = new ArrayList<>();

    static {
        map.put(2, "十");
        map.put(3, "百");
        map.put(4, "千");
        map.put(5, "万");
    }


    @Test
    public void run () {
        test(23);
    }


    public void test (int n) {
        StringBuilder sb = new StringBuilder();

        int t = 0;
        while (n > 0) {
            n /= 10;
            t++;
        }

        System.out.println(t);


    }
}
