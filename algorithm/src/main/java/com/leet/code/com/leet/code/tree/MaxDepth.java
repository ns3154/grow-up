package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/15 21:53
 **/
public class MaxDepth {


    public void run () {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        System.out.println(maxDepth(node));
    }


    public int maxDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }
        if (root.left == null && root.right == null) {
            return 1;
        }

        int left = maxDepth(root.left) + 1;
        int right = maxDepth(root.right) + 1;
        return Math.max(left, right);
    }

    public int maxDepth (TreeNode root, int dep) {
        if (null == root) {
            return dep;
        }
        dep++;
        int left = maxDepth(root.left, dep);
        int right = maxDepth(root.right, dep);

        return Math.max(left, right);


    }


}
