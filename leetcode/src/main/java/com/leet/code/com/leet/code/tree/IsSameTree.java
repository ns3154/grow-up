package com.leet.code.com.leet.code.tree;

import com.leet.code.com.leet.model.TreeNode;
import org.junit.jupiter.api.Test;

import javax.management.remote.rmi._RMIConnection_Stub;
import java.util.Deque;
import java.util.LinkedList;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/15 20:42
 **/
public class IsSameTree {


    @Test
    public void run () {
        TreeNode p = new TreeNode(1);
        p.left = new TreeNode(2);
        p.right = new TreeNode(3);
        p.left.left = new TreeNode(4);
        p.left.right = new TreeNode(5);

        TreeNode q = new TreeNode(1);
        q.left = new TreeNode(2);
        q.right = new TreeNode(3);
        q.left.left = new TreeNode(4);
        q.left.right = new TreeNode(1);

        System.out.println(isSameTree(p, q));

    }



    public boolean isSameTree(TreeNode p, TreeNode q) {

        if (null == p && null == q) {
            return true;
        }

        if (null == p || null == q) {
            return false;
        }

        if (p.val != q.val) {
            return false;
        }


        return isSameTree(p.left, q.left) && isSameTree(p.right, q.right);
    }


}
