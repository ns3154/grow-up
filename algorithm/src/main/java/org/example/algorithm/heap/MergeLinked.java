package org.example.algorithm.heap;

import com.sun.corba.se.impl.protocol.giopmsgheaders.LocateReplyMessage_1_0;

/**
 * <p>
 *     合并KEY个 升序链表
 * </p>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/28 11:31
 **/
public class MergeLinked {


    public static void main(String[] args) {
        ListNode[] lists = build();
        ListNode listNode = merge(lists[0], lists[1]);

        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }



    public static ListNode mergeKLists(ListNode[] lists) {
        int index = 0;
        int mid = lists.length / 2;
        ListNode listNode = mergeKList(lists, 0, mid);
        ListNode listNode1 = mergeKList(lists, mid + 1, lists.length);
        return merge(listNode, listNode1);
    }

    public static ListNode mergeKList(ListNode[] lists, int left, int right) {
        return null;
    }


    public static ListNode merge(ListNode n1, ListNode n2) {
        if (null == n1) {
            return n2;
        }
        if (null == n2) {
            return n1;
        }

        if (n1.val <= n2.val) {
            n1.next = merge(n1.next, n2);
            return n1;
        } else {
            n2.next = merge(n1, n2.next);
            return n2;
        }
    }

    public static ListNode mergeToList(ListNode n1, ListNode n2) {
        if (null == n2) {
            return n1;
        }

        if (null == n1) {
            return n2;
        }

        if (n1.val <= n2.val) {
//            n1.next =
        }
        return null;



    }


    public static ListNode[] build() {
        ListNode n1 = new ListNode();
        n1.val = 1;
        n1.next = new ListNode(4);
        n1.next.next = new ListNode(5);
        ListNode n2 = new ListNode();
        n2.val = 1;
        n2.next = new ListNode(3);
        n2.next.next = new ListNode(4);
        ListNode n3 = new ListNode();
        n3.val = 2;
        n3.next = new ListNode(6);

        return new ListNode[]{n1, n2, n3};
    }


    public static class ListNode {
        int val;
        ListNode next;
        ListNode() {}
        ListNode(int val) { this.val = val; }
        ListNode(int val, ListNode next) { this.val = val; this.next = next; }
    }
}
