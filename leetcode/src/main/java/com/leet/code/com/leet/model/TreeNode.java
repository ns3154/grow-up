package com.leet.code.com.leet.model;

import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/15 13:40
 **/
public class TreeNode {
    public int val;
    public TreeNode left;
    public TreeNode right;

    public TreeNode() {
    }

    public TreeNode(int val) {
        this.val = val;
    }

    TreeNode(int val, TreeNode left, TreeNode right) {
        this.val = val;
        this.left = left;
        this.right = right;
    }

    public static TreeNode build () {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        return node;
    }

    public static TreeNode arrayBuild (Integer[] arr) {
        TreeNode root = new TreeNode(arr[0]);
        Deque<TreeNode> deque = new LinkedList<>();
        deque.push(root);
        int i = 0;
        while (i < arr.length) {
            TreeNode poll = deque.poll();
            if (null != poll) {
                i = i + 1;
                if (i < arr.length) {
                    Integer t = arr[i];
                    if (null != t) {
                        poll.left = new TreeNode(t);
                        deque.addLast(poll.left);
                    }
                }

                i = i + 1;
                if (i < arr.length) {
                    Integer t = arr[i];
                    if (null != t) {
                        poll.right = new TreeNode(t);
                        deque.addLast(poll.right);
                    }
                }
            }
        }

        return root;
    }
}
