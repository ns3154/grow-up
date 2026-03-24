package com.leet.code.com.leet.easy;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * https://leetcode.cn/problems/maximum-number-of-pairs-in-array/
 * </p>
 *
 * @author Ns
 * @version 1.0
 * @date 2023/02/16 20:31
 **/
public class NumberOfPairs {

    public void run () {
        int[] nums = {1,3,2,1,3,2,2};
        int[] ints = numberOfPairsV2(nums);

        System.out.println(ints[0]);
        System.out.println(ints[1]);

    }

    public int[] numberOfPairs(int[] nums) {
        if (nums.length == 0) {
            return new int[]{0, 1};
        }
        int n = 0;
        Set<Integer> set = new HashSet<>();

        for (int num : nums) {
            if (!set.add(num)) {
                n++;
                set.remove(num);
            }
        }

        return new int[]{n, set.size()};
    }

    public int[] numberOfPairsV2(int[] nums) {
        int[] t = new int[101];
        for (int num : nums) {
            t[num]++;
        }

        int a = 0;
        int b = 0;
        for (int i : t) {
            a += i / 2;
            b += i % 2;
        }

        return new int[]{a, b};
    }
}
