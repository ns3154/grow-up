package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;

import java.util.HashMap;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/16 19:17
 **/
public class HasPathSum {


    public void run() {

        TreeNode root = TreeNode.arrayBuild(new Integer[]{5, 4, 8, 11, null, 13, 4, 7, 2, null, null, null, 1});
        System.out.println(hasPathSum(root, 22));
        System.out.println(hasPathSum1(root, 22));

    }


    public boolean hasPathSum1(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }
        sum(root, targetSum, 0);
        return ret;
    }

    public boolean hasPathSum(TreeNode root, int targetSum) {
        if (null == root) {
            return false;
        }
        if (null == root.left && null == root.right) {
            return root.val == targetSum;
        }

        return hasPathSum(root.left, targetSum - root.val) || hasPathSum(root.right, targetSum - root.val);
    }

    boolean ret = false;
    public void sum(TreeNode root, int targetSum, int sum) {
        if (null == root) {
            return;
        }
        sum += root.val;
        if (null == root.left && null == root.right) {
            if (sum == targetSum) {
                ret = true;
                return;
            }
        }

        if (root.left != null) {
             sum(root.left, targetSum, sum);
        }

        if (root.right != null) {
            sum(root.right, targetSum, sum);
        }
    }
}
