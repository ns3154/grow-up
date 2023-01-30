package com.leet.code.com.leet.code;

import java.util.HashMap;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * https://leetcode.cn/problems/lexicographical-numbers/
 * </p>
 *  1 <= n <= 5 * 104
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/09 17:19
 **/
public class LexicalOrder {


    @Test
    public void run () {

        List<Integer> ss = lexicalOrder(34);
        ss.forEach(System.out::println);

    }

    public List<Integer> lexicalOrder(int n) {
        List<Integer> ret = new ArrayList<>();
        int number = 1;
        for (int i = 1; i <= n; i++) {
            ret.add(number);
            if (i * number * 10 < n) {
                number = i * number * 10;
            } else {

            }

        }

        return ret;
    }

}
