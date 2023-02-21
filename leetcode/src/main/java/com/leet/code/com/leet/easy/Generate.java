package com.leet.code.com.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import org.junit.jupiter.api.Test;

public class Generate {

    @Test
    public void run () {
        System.out.println(generate(5).toString());
    }

    public List<List<Integer>> generate(int numRows) {
        List<List<Integer>> res = new ArrayList<>();
        res.add(Arrays.asList(1));
        for (int i = 1; i < numRows; i++) {
            List<Integer> prev = res.get(i - 1);
            List<Integer> curList = new ArrayList<>();
            for (int j = 0; j <= i; j++) {
                int left = 0;
                int right = 0;
                if (j > 0) {
                    left = prev.get(j - 1);
                }
                if (j < prev.size()) {
                    right = prev.get(j);
                }

                curList.add(left + right);
            }
            res.add(curList);
        }
        return res;
    }
}
