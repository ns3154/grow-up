package org.example.linked;

import org.assertj.core.data.Index;
import org.example.algorithm.leecode.model.ListNode;

/**
 * https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 *  删除链表倒数第N个节点
 * @author 杨帮东
 * @date 2022-01-19
 */
public class RemoveNthFromEnd {

    public void test () {
        ListNode node = ListNode.buildDefault();
        node.print();
        ListNode node1 = removeNthFromEnd(node, 2);
        node1.print();;

        ListNode node3 = new ListNode(1);
        node3.next = new ListNode(2);
        ListNode node2 = removeNthFromEnd(node3, 2);
        node2.print();

    }

    public ListNode removeNthFromEnd(ListNode head, int n) {
        int i = removeNthFromEnd1(head, n);
        if (i == n) {
            head = head.next;
        }
        return head;
    }

    public int removeNthFromEnd1(ListNode head, int n) {
        if (null == head.next) {
            return 1;
        }

        int z = 1 + removeNthFromEnd1(head.next, n);

        if (z == (n + 1)) {
            head.next = head.next.next;

        }
        return z;
    }

}
