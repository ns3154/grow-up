package com.leet.code.com.leet.easy;

import java.util.Arrays;

/**
 *
 * https://leetcode.cn/problems/build-array-from-permutation/
 * @author yang
 * @date 2023/02/18
 */
public class BuildArray {

    public void run() {
        int[] nums = new int[] {0,2,1,5,3,4};
        Arrays.stream(buildArray(nums)).forEach(i -> System.out.print(i + ","));
    }

    /**
     * 输入：nums = [0,2,1,5,3,4]
     * 输出：[0,1,2,4,5,3]
     *
     * @return {@link double[]}
     */
    public int[] buildArray(int[] nums) {
        int len = nums.length;

        int[] ans = new int[len];

        for (int i = 0; i < len; i++) {
            ans[i] = nums[nums[i]];
        }

        return ans;
    }

}
