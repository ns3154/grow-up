package org.example.linked;

import org.example.algorithm.leecode.model.ListNode;
import org.junit.jupiter.api.Test;

import java.util.Optional;

/**
 * https://leetcode-cn.com/problems/linked-list-cycle/
 * 判断链表 有环
 * @author 杨帮东
 * @date 2022-01-18
 */
public class HasCycle {


    @Test
    public void test () {
        ListNode node = new ListNode(3);
        ListNode node1 = new ListNode(2);
        ListNode node2 = new ListNode(0);
        ListNode node3 = new ListNode(-4);
        ListNode node4 = new ListNode(-2);
        node.next = node1;
        node1.next = node2;
        node2.next = node3;
        node3.next = node4;
        node4.next = node1;
        ListNode listNode = getCycleStart(node);
        System.out.println(Optional.ofNullable(listNode).orElse(new ListNode(-1)).val);

    }


    public boolean hasCycle(ListNode head) {
        if (null == head || null == head.next || null == head.next.next) {
            return false;
        }
        ListNode quick = head.next.next;
        ListNode slow = head.next;

        while (null != quick && null != quick.next) {
            if (slow == quick) {
                return true;
            }
            slow = slow.next;
            quick = quick.next.next;
        }
        return false;
    }


    public ListNode getCycleStart(ListNode head) {

        ListNode slow , fast;
        slow = fast = head;
        while (null != fast && null != fast.next) {
            slow = slow.next;
            fast = fast.next.next;
            if (slow == fast) {
                break;
            }
        }

        if (null == fast || null == fast.next) {
            return null;
        }

        slow = head;

        while (slow != fast) {
            slow = slow.next;
            fast = fast.next;
        }

        return slow;
    }
}
