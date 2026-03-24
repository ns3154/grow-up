package com.leet.code.com.leet.code.list;

import com.leet.code.com.leet.model.ListNode;

/**
 * <p>
 *
 * </p>
 *
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/19 21:28
 **/
public class RemoveElements {

    public ListNode removeElements(ListNode head, int val) {

        if (null == head) {
            return null;
        }


        while (null != head && head.val == val) {
            head = head.next;
        }

        if (null == head) {
            return head;
        }

        head.next = removeElements(head.next, val);

        return head;
    }


}
