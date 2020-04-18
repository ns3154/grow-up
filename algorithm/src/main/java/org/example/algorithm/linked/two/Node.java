package org.example.algorithm.linked.two;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/18 19:28
 **/
public class Node<T> {

    private T t;

    private Node<T> prev;

    private Node<T> next;

    public Node() {
        // nothing
    }

    public Node(T t, Node<T> prev, Node<T> next) {
        this.t = t;
        this.prev = prev;
        this.next = next;
    }

    public T getT() {
        return t;
    }

    public void setT(T t) {
        this.t = t;
    }

    public Node<T> getPrev() {
        return prev;
    }

    public void setPrev(Node<T> prev) {
        this.prev = prev;
    }

    public Node<T> getNext() {
        return next;
    }

    public void setNext(Node<T> next) {
        this.next = next;
    }
}
