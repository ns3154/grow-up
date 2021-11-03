package org.example.algorithm.linked;

import org.example.algorithm.leecode.model.ListNode;
import org.junit.jupiter.api.Test;

import java.util.List;

/**
 * <pre>
 *		反转链表
 * </pre>
 * @see https://leetcode-cn.com/problems/reverse-linked-list/
 * @author 杨帮东
 * @since 1.0
 * @date 2021/11/03 22:43
 **/
public class ReverseList {
	
	@Test
	public void test () {
		ListNode node = ListNode.build(new int[] {1, 2, 3, 4, 5});
		ListNode listNode = reverseList(node);
		listNode.print();
		
	}
	
	// 1 2 3 4 5
	// 5 4 3 2 1
	public ListNode reverseList(ListNode head) {
		if (null == head || null == head.next) {
			return head;
		}


		ListNode curr = head;
		ListNode prev = null;
		
		while (null != curr) {
			ListNode next = curr.next; // 2 3 4 5
			curr.next = prev; // 1 null
			
			prev = curr; // 1
			curr = next; // 2 3 4 5
			
			
		}
		return prev;
	}
	
	
}
