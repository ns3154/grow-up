package com.leet.code.com.leet.easy;


import java.util.HashMap;
import java.util.Map;

public class MajorityElement {

    public void run() {
        int[] nums = new int[] {6, 5, 5};
        System.err.println(majorityElementV2(nums));
    }


    public int majorityElement(int[] nums) {
        int len = nums.length;
        int tar = len / 2;
        Map<Integer, Integer> m = new HashMap<>();
        for (int i = 0; i < len; i++) {
            Integer integer = m.getOrDefault(nums[i], 0);
            m.put(nums[i], integer + 1);
        }

        for (Map.Entry<Integer, Integer> integerIntegerEntry : m.entrySet()) {
            if (integerIntegerEntry.getValue() > tar) {
                return integerIntegerEntry.getKey();
            }
        }

        return 0;
    }

    public int majorityElementV2(int[] nums) {
        int count = 0;
        Integer c = null;

        for (int num : nums) {
            if (count == 0) {
                c = num;
            }

            count += (num == c) ? 1 : -1;
        }


        return c;
    }
}
