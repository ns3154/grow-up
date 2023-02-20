package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class SearchInsert {

    @Test
    public void run() {
        int[] nums = new int[] {1,3,5,6};
        int target = 2;
        System.out.println(searchInsertV2(nums, target));

    }

    public int searchInsert(int[] nums, int target) {
        int len = nums.length;
        if (target < nums[0]) {
            return 0;
        }


        if (target > nums[len - 1]) {
            return len;
        }

        if (target == nums[len - 1]) {
            return len - 1;
        }

        int mid = len / 2 - 1;

        if (target == nums[mid]) {
            return mid;
        }

        if (target < nums[mid]) {
            for (int i = 0; i < mid; i++) {
                if (target <= nums[i]) {
                    return i;
                }
            }

        }

        for (int i = mid; i < len; i++) {
            if (target <= nums[i]) {
                return i;
            }
        }

        return 0;
    }

    public int searchInsertV2(int[] nums, int target) {
        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {
            int mid = (left + right) / 2;

            if (nums[mid] == target) {
                return mid;
            } else if (target > nums[mid]) {
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }

        return left;
    }
}
