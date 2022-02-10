package org.example.linked;

import org.example.algorithm.leecode.model.ListNode;
import org.junit.jupiter.api.Test;

public class Intersection {



    @Test
    public void test () {

    }


    public ListNode getIntersection(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        while (null != a && null != b) {
            if (a == b) {
                return a;
            }

            a = a.next;
            b = b.next;

            if (null == a) {
                a = headB;
            }

            if (null == b) {
                b = headA;
            }
        }

        return null;
    }

    public ListNode getintersectionV3(ListNode headA, ListNode headB) {
        ListNode a = headA;
        ListNode b = headB;

        while (a != b) {
            a = null == a ? headB : a.next;
            b = null == b ? headA : b.next;
        }

        return a;
    }
}
