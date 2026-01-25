package org.example.linked;

import org.example.algorithm.leecode.model.ListNode;

public class MergeLists {



    public void test() {
        ListNode node = ListNode.buildDefault();
        ListNode node1 = ListNode.buildDefault();
        ListNode node2 = ListNode.buildDefault();
        ListNode node3 = ListNode.buildDefault();
        ListNode[] list = {node, node1, node2, node3};
        ListNode listNode = mergeKLists(list);
        listNode.print();


//        ListNode listNode1 = mergeTwoLists(node, node1);
//        ListNode listNode2 = mergeTwoListsV2(node, node1);
//        listNode1.print();
//        listNode2.print();
    }

    /**
     * 合并两个列表
     * @return {@link ListNode}
     */
    public ListNode mergeTwoLists(ListNode list1, ListNode list2) {
        ListNode head = new ListNode(-1);
        ListNode p = head;

        if (null == list1 && null == list2) {
            return head.next;
        }

        if (null == list1) {
            return list2;
        }

        if (null == list2) {
            return list1;
        }

        while (null != list1 && null != list2) {
            if (list1.val > list2.val) {
                p.next = list2;
                list2 = list2.next;
            } else {
                p.next = list1;
                list1 = list1.next;
            }
            p = p.next;
        }

        if (null != list1) {
            p.next = list1;
        }
        if (null != list2) {
            p.next = list2;
        }

        return head.next;
    }

    /**
     * 合并两个列表v2
     * @return {@link ListNode}
     */
    public ListNode mergeTwoListsV2(ListNode list1, ListNode list2) {

        if (null == list1) {
            return list2;
        }

        if (null == list2) {
            return list1;
        }

        if (list1.val > list2.val) {
            list2.next = mergeTwoListsV2(list1, list2.next);
           return list2;
        } else {
            list1.next = mergeTwoListsV2(list1.next, list2);
            return list1;
        }
    }


    /**
     * 合并K个升序链表
     *
     * @param list 列表
     * @return {@link ListNode}
     */
    public ListNode mergeKLists(ListNode[] list) {
        if (null == list || list.length == 0) {
            return null;
        }

        return mergeKLists(list, 0, list.length - 1);
    }


    /**
     * 合并中
     *
     * @param list  列表
     * @param left  左
     * @param right 正确
     * @return {@code ListNode}
     */
    public ListNode mergeKLists(ListNode[] list, int left, int right) {
        if (left == right) {
            return list[left];
        }

        int mid = left + (right - left) / 2;
        ListNode leftNode = mergeKLists(list, left, mid);
        ListNode rightNode = mergeKLists(list, mid + 1, right);
        return mergeTwoLists(leftNode, rightNode);
    }
}
