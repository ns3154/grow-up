package com.leet.code.com.leet.easy;


public class Merge {

    public void run() {
        int[] nums1 = new int[] {0};
        int m = 0;
        int[] nums2 = new int[] {1};
        int n = 1;
        merge(nums1, m, nums2, n);
    }

    public void merge(int[] nums1, int m, int[] nums2, int n) {
        int index = m + n;
        int curIndex = index - 1;
        n--;
        m--;
        while (index-- >= 0) {
            if (m >= 0 && n >= 0) {
                if (nums2[n] >= nums1[m]) {
                    nums1[curIndex--] = nums2[n--];
                } else {
                    nums1[curIndex--] = nums1[m--];
                }
            } else if (m >= 0) {
                nums1[curIndex--] = nums1[m--];

            }else if (n >= 0) {
                nums1[curIndex--] = nums2[n--];
            }
        }
        for (int i : nums1) {
            System.out.println(i);
        }
    }
}
