package org.example.algorithm.recursion;

import org.example.algorithm.leecode.model.ListNode;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 *	https://leetcode-cn.com/problems/reorder-list/
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/11/01 22:01
 **/
public class ReorderList {
	
	@Test
	public void test () {
		ListNode build = ListNode.build(new int[]{1, 2, 3, 4, 5});
		build.print();
		reorderList1(build);
		build.print();
	}
	
	
	
	public void reorderList1 (ListNode head) {
		
		List<ListNode> list = new ArrayList<>();
		while (null != head) {
			list.add(head);
			head = head.next;
		}
		
		int i = 0, j = list.size() - 1;
		while (i < j) {
			list.get(i++).next = list.get(j);
			if (i == j) {
				break;
			}
			list.get(j--).next = list.get(i);
		}
		list.get(i).next = null;
	}
	
	public void reorderList2 (ListNode head) {
		ListNode h = head;
		int len = 0;
		while (null != h) {
			len++;
			h = h.next;
		}
		reorderList2Sub(head, len);
	}
	
	public ListNode reorderList2Sub (ListNode head, int len) {
	
		
		
		return null;
	}
	
}
