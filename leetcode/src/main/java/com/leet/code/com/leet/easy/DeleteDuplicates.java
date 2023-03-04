package com.leet.code.com.leet.easy;

import com.leet.code.com.leet.model.ListNode;
import org.junit.jupiter.api.Test;

public class DeleteDuplicates {

    @Test
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
