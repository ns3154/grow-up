package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/16 02:05
 **/
public class IsBalanced {

    public void run() {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        node.left.right.right = new TreeNode(5);
        node.left.right.right.right = new TreeNode(5);

        System.out.println(isBalanced(node));
    }


    public boolean isBalanced(TreeNode root) {
        return Math.abs(balanced(root.left) - balanced(root.right)) <= 1 && isBalanced(root.left) && isBalanced(root.right);
    }

    public int balanced(TreeNode root) {
        if (null == root) {
            return 0;
        }
        int left = balanced(root.left);
        int right = balanced(root.right);
        return Math.max(left, right) + 1;

    }
}
