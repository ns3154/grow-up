package org.example.algorithm.linked.simple;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/18 17:13
 **/
public class SimpleLinked<T> {

    private Node<T> first;


    public SimpleLinked() {
        // nothing
    }

    public void add(T t) {
        Node<T> node = new Node<>(t, null);
        if (null == first) {
            first = node;
        } else {
            Node<T> tmp  = first;
            while (null != tmp.getNext()) {
                tmp = tmp.getNext();
            }
            tmp.setNext(node);
        }
    }

    public T remove(T t) {
        Node<T> tmp = first;
        if (tmp.getT().equals(t)) {
            first = first.getNext();
            return tmp.getT();
        }

        Node<T> next;
        while (null != (next = tmp.getNext())) {
            if (next.getT().equals(t)) {
                tmp.setNext(next.getNext());
                return next.getT();
            }
            tmp = tmp.getNext();
        }

        return null;
    }

    public T find(T t) {
        Node<T> tmp = first;
        if (tmp.getT().equals(t)) {
            return tmp.getT();
        }

        Node<T> next;
        while (null != (next = tmp.getNext())) {
            if (next.getT().equals(t)) {
                return next.getT();
            }
        }
        return null;
    }

    private Node<T> findLastNode() {
        return null;
    }


}
