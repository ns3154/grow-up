package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/15 22:11
 **/
public class SortedArrayToBST {

    public void run() {
        int[] numbs = new int[] {-10,-3,0,5,9};
        TreeNode treeNode = sortedArrayToBST(numbs);
        System.out.println(treeNode);

    }

    public TreeNode sortedArrayToBST(int[] nums) {

        return sortedArrayToBST(nums, 0, nums.length - 1);
    }

    public TreeNode sortedArrayToBST(int[] nums, int left, int right) {
        if (left > right) {
            return null;
        }
        int mid = (left + right) / 2;

        TreeNode root = new TreeNode(nums[mid]);
        root.left = sortedArrayToBST(nums, left, mid - 1);
        root.right = sortedArrayToBST(nums, mid + 1, right);
        return root;
    }

}
