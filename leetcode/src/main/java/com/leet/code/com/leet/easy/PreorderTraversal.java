package com.leet.code.com.leet.easy;

import com.leet.code.com.leet.model.TreeNode;

import java.util.ArrayList;
import java.util.List;

public class PreorderTraversal {


    public static void main(String[] args) {


        TreeNode node = new TreeNode(1);

        node.right = new TreeNode(2);
        node.right.left = new TreeNode(3);

        PreorderTraversal traversal = new PreorderTraversal();
        List<Integer> integers = traversal.preorderTraversal(node);
        integers.forEach(System.err::println);

    }


    public List<Integer> preorderTraversal(TreeNode root) {
        List<Integer> rest = new ArrayList<>();
        rest.add(root.val);
        left(rest, root.left);
        right(rest, root.right);
        return rest;
    }


    private void left(List<Integer> rest, TreeNode node) {
        if (null == node) {
            return;
        }
        

        rest.add(node.val);
        left(rest, node.left);
        right(rest, node.right);
    }

    public void right (List<Integer> rest, TreeNode node) {
        if (null == node) {
            return;
        }


        rest.add(node.val);
        left(rest, node.left);
        right(rest, node.right);
    }
}
