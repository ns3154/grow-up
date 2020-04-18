package org.example.algorithm.linked.simple;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/18 17:16
 **/
public class Node<T> {

    private Node<T> next;

    private T t;

    public Node() {
        // noting
    }

    public Node(T t) {
        this.t = t;
    }

    public Node(T t,  Node<T> next) {
        this.t = t;
        this.next = next;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }
}
