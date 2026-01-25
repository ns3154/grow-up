package org.example.algorithm.linked;

import org.example.algorithm.leecode.model.Node;

/**
 * <p>
 *     desc
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/03 10:59
 **/
public class Insert {

    public void test () {
        Node node = Node.build(new int[] {1,3,5});
        Node insert = insert(node, 2);
        System.out.println(node);
    }

    public Node insert (Node head, int insertVal) {
        if (null == head) {
            Node node = new Node(insertVal);
            node.next = node;
            return node;
        }

        Node max = head;
        Node min = head;
        Node cur = head.next;
        while (head != cur) {
            if (max.val <= cur.val) {
                max = cur;
            }
            if (min.val > cur.val) {
                min = cur;
            }
            cur = cur.next;
        }

        Node node = new Node(insertVal);
        if (insertVal >= max.val || insertVal <= min.val) {
            node.next = max.next;
            max.next = node;
        } else {
            cur = head;
            while (true) {
                if (insertVal >= cur.val && insertVal <= cur.next.val) {
                    node.next = cur.next;
                    cur.next = node;
                    break;
                }
                cur = cur.next;
            }
        }

        return head;
    }


}
