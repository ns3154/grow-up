package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class MaximumCount {

    @Test
    public void run() {
        int[] nums = new int[] {-2,-1,-1, 0, 0,1, 1,2,3};
        System.out.println(maximumCount(nums));
    }


    public int maximumCount(int[] nums) {
        int left = 0;
        int right = nums.length - 1;
        while (left <= right) {

            int mid = (left + right) / 2;
            if (nums[mid] <= 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        int r = left;

        left = 0;
        right = nums.length - 1;

        while (left <= right) {

            int mid = (left + right) / 2;

            if (nums[mid] < 0) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }

        }

        return Math.max(nums.length - r, right + 1);
    }
}
