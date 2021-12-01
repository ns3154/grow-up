package org.example.algorithm.leecode.model;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/31 00:55
 **/
public class ListNode {

    public int val;
    public ListNode next;

    public ListNode () {
    }

    public ListNode (int val) {
        this.val = val;
    }

    ListNode (int val, ListNode next) {
        this.val = val;
        this.next = next;
    }

    public static ListNode build (int[] arrgs) {
        ListNode node = new ListNode(arrgs[0]);
        ListNode cur = node;
        for (int i = 1; i < arrgs.length; i++) {
            node = node.next = new ListNode(arrgs[i]);
        }
        return cur;
    }

    public static ListNode buildDefault () {
        return build(new int[]{1, 2, 3, 4, 5, 6, 7});
    }

    public void print () {
        StringBuilder sb = new StringBuilder();
        sb.append(val);
        ListNode node = next;
        while (null != node) {
            sb.append(",");
            sb.append(node.val);
            node = node.next;
        }
        System.out.println(sb.toString());
    }
}
