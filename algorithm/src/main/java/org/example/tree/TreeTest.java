package org.example.tree;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Random;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/28 18:27
 **/
public class TreeTest {

    @Test
    public void BinarySearchTreeTest() {
        BinarySearchTree tree = new BinarySearchTree(500);
        Random random = new Random();
        HashSet<Integer> set = new HashSet<>();
        while (set.size() <= 100) {
            int t = random.nextInt(1000);
            if (t != 500) {
                set.add(t);
            }
        }

        set.forEach(tree::insert);

//        List<String> show = tree.preOrder();
//        show.forEach(System.out::println);
        System.out.println(tree.height(tree.getRoot()));
    }
}
