package com.leet.code.com.leet.code;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

/**
 *
 * <a href="https://leetcode-cn.com/problems/two-sum/">两数之和</a>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/12 10:47
 **/
public class One {

    public static int[] twoSun(int[] nums, int target) {
        Map<Integer, Integer> map = new HashMap<>();
        for (int i = 0, size = nums.length; i < size; i++) {
            int z = target - nums[i];
            if (z >= 0 && map.containsKey(z)) {
                return new int[]{map.get(z), i};
            }
            map.put(nums[i], i);
        }
        return null;
    }

    public static void main(String[] args) {
        int[] nums = {2, 7, 11, 15};
        int target = 9;
        System.out.print(Arrays.toString(twoSun(nums, target)));
    }

}
