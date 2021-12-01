package org.example.algorithm.linked;

import org.example.algorithm.leecode.model.ListNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *     https://leetcode-cn.com/problems/remove-nth-node-from-end-of-list/
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/12/01 10:38
 **/
public class RemoveNthFromEnd {

    @Test
    public void test () {
        ListNode listNode = ListNode.buildDefault();
        listNode.print();
        ListNode listNode1 = removeNthFromEndForPointer(listNode, 7);
        if (null != listNode1) {
            listNode1.print();
        }
    }

    public ListNode removeNthFromEndForList (ListNode head, int n) {
        List<ListNode> list = new ArrayList<>();
        while (null != head) {
            list.add(head);
            head = head.next;
        }
        int r = list.size() - n;

        if (r == 0) {
            return list.get(0).next;
        }

        list.get(r - 1).next = list.get(r).next;
        return list.get(0);
    }

    public ListNode removeNthFromEndForPointer(ListNode head, int n) {
        if (null == head) {
            return head;
        }

        ListNode a = head;
        ListNode b = head;
        ListNode c = head;

        while (n > 0) {
            a = a.next;
            n--;
        }

        if (null == a) {
            return head.next;
        }

        while (null != a.next) {
            a = a.next;
            b = b.next;
            c = c.next;
        }
        b.next = c.next.next;
        return head;
    }
}
