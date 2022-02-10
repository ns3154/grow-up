package org.example.linked;

import org.example.algorithm.leecode.model.ListNode;
import org.junit.jupiter.api.Test;

/**
 * 反转链表
 * https://leetcode-cn.com/problems/reverse-linked-list/
 * @author 杨帮东
 * @date 2022-01-19
 */
public class ReverseList {


    @Test
    public void test () {
        ListNode build = ListNode.build(new int[]{1, 2, 3, 4, 5, 6, 7});
        build.print();
        ListNode node = reverseBetween(build, 2, 4);
        node.print();

    }



    /**
     * 反转列表
     * 1 2 3 4 5
     * 5 4 3 2 1
     * @param head 头
     * @return {@link ListNode}
     */
    public ListNode reverseList (ListNode head) {
        if (null == head || null == head.next) {
            return head;
        }
        ListNode node = new ListNode(-1);
        reverseList(head, node);
        return node.next;
    }

    public ListNode reverseListV2 (ListNode head) {
        if (null == head.next) {
            return head;
        }

        ListNode node = reverseListV2(head.next);
        head.next.next = head;
        head.next = null;
        return node;
    }

    public ListNode reverseList (ListNode head, ListNode node) {
        if (null != head.next) {
            ListNode tail = reverseList(head.next, node);
            tail.next = head;
            head.next = null;
            return tail.next;
        }
        node.next = head;
        return  node.next;
    }


    public ListNode reverseBetween (ListNode head, int left, int right) {
        if (left == 1) {
            return reverseN(head, right);
        }
        head.next = reverseBetween(head.next, --left, --right);
        return head;
    }

    ListNode suc = null;
    public ListNode reverseN(ListNode head, int n) {
        if (n == 1) {
            suc = head.next;
            return head;
        }
        ListNode node = reverseN(head.next, --n);
        head.next.next = head;
        head.next = suc;
        return node;
    }


    @Test
    public void testKGroup () {
        ListNode build = ListNode.build(new int[]{1, 2, 3, 4, 5});
        build.print();
        ListNode node = reversekGroup(build, 2);
        node.print();
    }

    /**
     * https://leetcode-cn.com/problems/reverse-nodes-in-k-group/
     * K个一组 反转链表
     *
     * @param head 头
     * @param k    k
     * @return {@link ListNode}
     */
    public ListNode reversekGroup (ListNode head, int k) {
        return reverseK(head.next, --k, k);
    }

    ListNode after = null;
    ListNode noReverse = null;
    public ListNode reverseK (ListNode head, int n, int k) {

        if (n == 1) {
            return reverseK(head.next, k, k);
        }

        if (null == head) {
            return null;
        }

        if (n == 1) {
            n = k;
            suc = head.next;
            return head;
        }

        ListNode node = reversekGroup(head.next, --n);
        if (null != node) {
            head.next.next = head;
            head.next = suc;
        }
        return node;
    }






}
