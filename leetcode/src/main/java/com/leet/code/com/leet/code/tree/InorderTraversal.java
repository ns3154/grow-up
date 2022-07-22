package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;
import org.junit.jupiter.api.Test;

import java.lang.reflect.WildcardType;
import java.util.*;

/**
 * <p>
 *      https://leetcode.cn/problems/binary-tree-inorder-traversal/
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/15 13:41
 **/
public class InorderTraversal {



    @Test
    public void run () {
        TreeNode node = new TreeNode(1);
        node.left = new TreeNode(2);
        node.right = new TreeNode(3);
        node.left.left = new TreeNode(4);
        node.left.right = new TreeNode(5);
        List<Integer> integers = inorderTraversal(node);
        integers.forEach(System.out::print);
        List<Integer> integers1 = whileOrder(node);
        integers1.forEach(System.out::print);

    }

    public List<Integer> inorderTraversal(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }
        inOrder(root, list);
        return list;
    }

    public void inOrder(TreeNode root, List<Integer> list) {

        if (null == root) {
            return;
        }
        inOrder(root.left, list);
        list.add(root.val);
        inOrder(root.right, list);

    }

    public List<Integer> whileOrder(TreeNode root) {
        List<Integer> list = new ArrayList<>();
        if (null == root) {
            return list;
        }

        Deque<TreeNode> stk = new LinkedList<>();

        while (null != root || !stk.isEmpty()) {
            while (null != root) {
                stk.push(root);
                root = root.left;
            }

            TreeNode pop = stk.pop();
            list.add(pop.val);
            root = pop.right;
        }
        return list;
    }

    public List<Integer> morris (TreeNode root) {
        List<Integer> list = new ArrayList<>();


        return list;
    }
}
