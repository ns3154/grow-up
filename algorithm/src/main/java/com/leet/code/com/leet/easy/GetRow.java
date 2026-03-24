package com.leet.code.com.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.leet.code.com.leet.model.ListNode;

/**
 * <p>
 *
 * </p>
 *
 * @author Ns
 * @version 1.0
 * @date 2023/02/23 18:21
 **/
public class GetRow {




    public void run () {
        getRow(1);
    }


    public List<Integer> getRow(int rowIndex) {
        List<Integer> res = Arrays.asList(1);
        for (int i = 1; i <= rowIndex; i++) {
            int size = res.size() + 2 - 1;

            List<Integer> cur = new ArrayList<>();
            cur.add(1);
            for (int j = 1; j < size - 1; j++) {
                cur.add(res.get(j) + res.get(j - 1));
            }
            cur.add(1);
            res = cur;
        }

        return res;
    }
}
