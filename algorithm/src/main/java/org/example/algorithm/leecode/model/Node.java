package org.example.algorithm.leecode.model;

import java.io.IOException;
import java.io.ObjectInputStream;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/03 10:59
 **/
public class Node {
    public int val;
    public Node next;

    public Node () {
    }

    public Node (int _val) {
        val = _val;
    }

    public Node (int _val, Node _next) {
        val = _val;
        next = _next;
    }

    public static Node build(int[] ints) {
        Node node = new Node(ints[0]);
        Node cur = node;
        for (int i = 1; i < ints.length; i++) {
            node = node.next = new Node(ints[i]);
        }
        node.next = cur;
        return cur;
    }


}
