package org.example.linked;

import org.example.algorithm.leecode.model.ListNode;

public class MiddleNode {




    public void test () {
        ListNode node = new ListNode(1);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(3);
        ListNode node3 = new ListNode(4);
        ListNode node4 = new ListNode(5);
        ListNode node5 = new ListNode(6);
        ListNode node6 = new ListNode(7);
        ListNode node7 = new ListNode(8);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node5;
        node5.next = node6;
//        node6.next = node7;
        System.out.println(middleNode(node).val);
    }

    public ListNode middleNode(ListNode head) {

        ListNode slow, fast;
        slow = fast = head;

        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
        }

        return slow;
    }
}
