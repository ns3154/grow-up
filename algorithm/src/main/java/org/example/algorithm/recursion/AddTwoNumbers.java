package org.example.algorithm.recursion;

import org.example.algorithm.leecode.model.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *      两数相加
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/29 23:53
 **/
public class AddTwoNumbers {

    public static void main (String[] args) {
        ListNode n1 = new ListNode(9);
        n1.next = new ListNode(9);
        n1.next.next = new ListNode(9);
        n1.next.next.next = new ListNode(9);
        n1.next.next.next.next = new ListNode(9);
        n1.next.next.next.next.next = new ListNode(9);
        n1.next.next.next.next.next.next = new ListNode(9);


        ListNode n2 = new ListNode(9);
        n2.next = new ListNode(9);
        n2.next.next = new ListNode(9);
        n2.next.next.next = new ListNode(9);

        AddTwoNumbers s = new AddTwoNumbers();
        ListNode listNode = s.addTwoNumbers(n1, n2);

        System.out.println("数据校验--------------");
        while (null != n1) {
            System.out.println(n1.val);
            n1 = n1.next;
        }
        System.out.println("--------------");

        while (null != n2) {
            System.out.println(n2.val);
            n2 = n2.next;
        }
        System.out.println("数据校验--------------");
        while (null != listNode) {
            System.out.println(listNode.val);
            listNode = listNode.next;
        }
    }

    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        return linked(l1, l2);
    }

    public ListNode linked(ListNode l1, ListNode l2) {
         int next = 0;
         ListNode node = new ListNode(next);
         ListNode tmp = node;
         while (null != l1 || null != l2 || next > 0) {
             ListNode n = new ListNode(next);
             int i1 = null == l1 ? 0 : l1.val;
             int i2 = null == l2 ? 0 : l2.val;
             n.val = i1 + i2 + n.val;
             if (n.val > 9) {
                 n.val = n.val % 10;
                 next = 1;
             } else {
                 next = 0;
             }

             if (null != l1) {
                 l1 = l1.next;
             }

             if (null != l2) {
                 l2 = l2.next;
             }

             node.next = n;
             node = node.next;
         }


        return tmp.next;
    }

    // 递归
    public ListNode oneMethod(ListNode l1, ListNode l2) {
        List<Integer> list1 = new ArrayList<>();
        List<Integer> list2 = new ArrayList<>();
        while (null != l1) {
            list1.add(l1.val);
            l1 = l1.next;
        }
        while (null != l2) {
            list2.add(l2.val);
            l2 = l2.next;
        }
        return add(list1, list2, 0, 0);
    }

    public ListNode add(List<Integer> l1, List<Integer> l2, int nextNum, int count) {
        ListNode node = new ListNode(nextNum);
        if (count > Math.max(l1.size(), l2.size()) && nextNum == 0) {
            return null;
        }


        int i1 = count < l1.size() ? l1.get(count) : 0;
        int i2 = count < l2.size() ? l2.get(count) : 0;
        int val = i1 + i2;

        node.val += val;
        if (node.val > 9) {
            node.val = node.val % 10;
            nextNum = 1;
        } else {
            nextNum = 0;
        }

        node.next = add(l1, l2, nextNum, ++count);
        return node;
    }

}
