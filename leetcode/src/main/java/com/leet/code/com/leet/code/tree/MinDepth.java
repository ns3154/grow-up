package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/16 15:00
 **/
public class MinDepth {

    @Test
    public void run () {
        TreeNode root = TreeNode.build();

        System.out.println(minDepth(root));
        System.out.println(stack(root));
    }

    // 递归
    public int minDepth(TreeNode root) {
        if (null == root) {
            return 0;
        }

        if (root.left == null && root.right == null) {
            return 1;
        }
        int min = Integer.MAX_VALUE;
        if (null != root.left) {
            min = Math.min(minDepth(root.left) + 1, min);
        }
        if (null != root.right) {
            min = Math.min(minDepth(root.right) + 1, min);

        }

        return min;
    }


    // 栈
    public int stack (TreeNode root) {
        if (null == root) {
            return 0;
        }

        int len = 1;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while (!queue.isEmpty()) {

            int size = queue.size();
            for (int i = 0; i < size; i++) {
                TreeNode poll = queue.poll();
                if (null == poll.right && null == poll.left) {
                    return len;
                }

                if (null != poll.left) {
                    queue.offer(poll.left);
                }

                if (null != poll.right) {
                    queue.offer(poll.right);
                }
            }
            len++;
        }
        return len;
    }


}
