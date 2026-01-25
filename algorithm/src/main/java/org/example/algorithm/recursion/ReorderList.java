package org.example.algorithm.recursion;

import org.example.algorithm.leecode.model.ListNode;

import java.util.ArrayList;
import java.util.List;

/**
 * <pre>
 *
 * </pre>
 * @see https://leetcode-cn.com/problems/reorder-list/
 * @author 杨帮东
 * @since 1.0
 * @date 2021/11/01 22:01
 **/
public class ReorderList {

    public void test () {
        ListNode build = ListNode.build(new int[]{1, 2, 3, 4, 5, 6, 7});
        build.print();
        reorder(build);
        build.print();
    }

    /**
     * <a>1 2 3 4 5 6 7</a>
     * <br>
     * <a>1 7 2 6 3 5 4</a>
     *
     * @param nodes 节点
     */
    public void reorder (ListNode nodes) {
        List<ListNode> list = new ArrayList<>();
        while (null != nodes) {
            list.add(nodes);
            nodes = nodes.next;
        }

        for (int i = 0; i < list.size(); i++) {
            int index = list.size() - 1 - i;
            if (index <= i) {
                list.get(i).next = null;
                break;
            }
            list.get(i).next = list.get(index);

            if (i + 1 < list.size()) {
                list.get(index).next = list.get(i + 1);
            }

        }
        nodes = list.get(0);
    }

}
