package com.leet.code.com.leet.code;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/10/13 11:14
 **/
public class Two {


    public static void main(String[] args) {

        ListNode left = new ListNode(2);
        ListNode left1 = new ListNode(4);
        ListNode left2 = new ListNode(3);
        left.next = left1;
        left1.next = left2;


        ListNode right = new ListNode(5);
        ListNode right1 = new ListNode(6);
        ListNode right2 = new ListNode(4);

        right.next = right1;
        right1.next = right2;
        ListNode listNode = addTwoNumbers(left, right);
    }

    public static ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode pre = new ListNode(0);
        ListNode cur = pre;
        int carry = 0;
        while (l1 != null || l2 != null) {
            int x = l1 == null ? 0 : l1.val;
            int y = l2 == null ? 0 : l2.val;
            int sum = x + y + carry;

            carry = sum / 10;
            sum = sum % 10;
            cur.next = new ListNode(sum);

            cur = cur.next;
            if (l1 != null) l1 = l1.next;
            if (l2 != null) l2 = l2.next;
        }
        if (carry == 1) {
            cur.next = new ListNode(carry);
        }
        return pre.next;
    }

    static class ListNode {
        int val;
        ListNode next;

        ListNode() {
        }

        ListNode(int val) {
            this.val = val;
        }

        ListNode(int val, ListNode next) {
            this.val = val;
            this.next = next;
        }
    }
}
