package org.example.algorithm.linked.two;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/18 19:30
 **/
public class TwoWayLinked<T> {

    private Node<T> node;


    public void add(T t) {
        if (null == node) {
            node = new Node<>(t, null, null);
            return;
        }

        Node<T> tmp = node;
        while (tmp.getNext() != null) {
            tmp = tmp.getNext();

        }

        tmp.setNext(new Node<>(t, tmp, null));
    }

    public T remove(T t) {
        if (node.getT().equals(t) && null == node.getNext()) {
            node = new Node<>();
        }




        return null;
    }
}
