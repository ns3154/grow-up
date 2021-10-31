package org.example.algorithm.recursion;

import org.example.algorithm.heap.MergeLinked;
import org.example.algorithm.leecode.model.ListNode;

/**
 * <pre>
 * 		合并两个有序链表
 * 	https://leetcode-cn.com/problems/merge-two-sorted-lists/
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/31 00:52
 **/
public class MergeTwoLists {
	
	public static void main (String[] args) {
		ListNode n1 = ListNode.build(new int[]{1, 2, 4});
		ListNode n2 = ListNode.build(new int[]{1, 3, 4});
		MergeTwoLists mergeTwoLists = new MergeTwoLists();
		ListNode listNode = mergeTwoLists.mergeTwoLists(n1, n2);
		listNode.print();
		
	}
	
	
	public ListNode mergeTwoLists(ListNode l1, ListNode l2) {
		if (null == l1 && null == l2) {
			return null;
		}
		if (null == l1) {
			return l2;
		}
		if (null == l2) {
			return l1;
		}
		if (l1.val > l2.val) {
			l2.next = mergeTwoLists(l1, l2.next);
			return l2;
		} else {
			l1.next = mergeTwoLists(l1.next, l2);
			return l1;
		}
	
	}
	
	

}
