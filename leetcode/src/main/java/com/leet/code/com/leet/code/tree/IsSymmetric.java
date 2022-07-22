package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;
import org.junit.jupiter.api.Test;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/15 21:35
 **/
public class IsSymmetric {


    @Test
    public void run () {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(2);
        node.left.left = new TreeNode(3);
        node.left.right = new TreeNode(4);
        node.right.left = new TreeNode(4);
        node.right.right = new TreeNode(3);
        System.out.println(isSymmetric(node));
    }

    public boolean isSymmetric(TreeNode root) {
        if (null == root) {
            return false;
        }

        return isSymmtric(root.left, root.right);
    }

    public boolean isSymmtric(TreeNode left, TreeNode right) {

        if (null == left && null == right) {
            return true;
        }

        if (null == left || null == right) {
            return false;
        }


        if (left.val != right.val) {
            return false;
        }

        return isSymmtric(left.left, right.right) && isSymmtric(left.right, right.left);
    }
}
