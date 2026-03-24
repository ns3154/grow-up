package com.leet.code.com.leet.easy;

import com.leet.code.com.leet.model.ListNode;

public class DeleteDuplicates {

    public void run() {

    }

    public ListNode deleteDuplicates(ListNode head) {
        if (null == head) {
            return head;
        }

        int curV = head.val;

        ListNode curN = head;
        while (null != curN.next) {
            if (curV == curN.next.val) {
                curN.next = curN.next.next;
            } else {
                curN = curN.next;
                curV = curN.val;
            }

        }

        return head;
    }

}
