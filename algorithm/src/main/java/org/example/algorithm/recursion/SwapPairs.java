package org.example.algorithm.recursion;

import org.example.algorithm.leecode.model.ListNode;

/**
 * <pre>
 *		给定一个链表，两两交换其中相邻的节点，并返回交换后的链表
 *		https://leetcode-cn.com/problems/swap-nodes-in-pairs/
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/31 01:20
 **/
public class SwapPairs {
	
	public void test() {
		ListNode listNode = swapPairs(ListNode.build(new int[]{1, 2, 3, 4, 5}));
		listNode.print();
	}
	
	public ListNode swapPairs(ListNode head) {
		if (null == head || null == head.next) {
			return head;
		}
		ListNode result = head.next;
		head.next = swapPairs(result.next);
		result.next = head;
		return result;
	}
	

}
