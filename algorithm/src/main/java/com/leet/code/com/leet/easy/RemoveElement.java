package com.leet.code.com.leet.easy;


public class RemoveElement {

    public void run () {
        int[] nums = new int[] {3, 3};
        int value = 5;
        System.out.println(removeElement(nums, value));
    }

    public int removeElement(int[] nums, int val) {
        int left = 0;

        if (nums.length == 1) {
            return nums[0] == val ? 0 : 1;
        }

        for (int right = 0; right < nums.length; right++) {
            int tem = nums[left];
            if (tem == val) {
                nums[left] = nums[right];
                nums[right] = tem;
            }

            if (nums[left] != val) {
                left++;
            }
        }

        return left;
    }

    public int removeElementV2(int[] nums, int val) {
        int left = 0;
        for (int right = 0; right < nums.length; right++) {
            if (nums[right] != val) {
                nums[left++] = nums[right];
            }
        }
        return left;
    }


}
