package org.example.algorithm.linked.simple;

import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/18 17:23
 **/
public class Test {

    public static void main(String[] args) {
        SimpleLinked<Integer> simpleLinked = new SimpleLinked<>();
        simpleLinked.add(1);
        simpleLinked.add(2);
        simpleLinked.add(3);
        //9
        simpleLinked.add(4);
        simpleLinked.add(5);
        simpleLinked.add(6);
//        Integer remove = simpleLinked.remove(3);
//        Integer find = simpleLinked.find(2);
//        System.out.println(simpleLinked);
//        simpleLinked.insert(9, 3);
//        simpleLinked.deleteByIndex(6);
        Node<Integer> integerNode = simpleLinked.reverseListNode();
        simpleLinked.out();
    }
}
