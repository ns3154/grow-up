package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class RemoveDuplicates {

    @Test
    public void run() {
        int[] nums = {0,0,1,1,1,2,2,3,3,4};

        System.out.println(removeDuplicates(nums));
    }


    public int removeDuplicates(int[] nums) {
        int cur = nums[0];
        int curIndex = 1;

        for (int i = 1; i < nums.length; i++) {
            int num = nums[i];
            if (cur != num) {
                cur = num;
                nums[curIndex++] = num;
            }
        }
        return curIndex;
    }


}
