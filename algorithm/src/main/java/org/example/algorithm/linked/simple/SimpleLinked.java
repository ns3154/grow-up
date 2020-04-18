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

    private int size;


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
        size++;
    }

    public void insert(T t, int index) {
        if (index > size || index < 0) {
            throw new ArrayIndexOutOfBoundsException("index > size || index < 0");
        }
        int j = index - 1;
        Node<T> node = new Node<>(t);
        Node<T> tmp = first;
        if (j == -1) {
            first = node;
            first.setNext(tmp);
            size++;
            return;
        }

        int i = 0;
        while (i < j) {
            tmp = tmp.getNext();
            i++;
        }
        Node<T> next = tmp.getNext();
        node.setNext(next);
        tmp.setNext(node);
        size++;
    }

    public void deleteByIndex(int index) {
        int j = index - 1;
        Node<T> tmp = first;
        if (j < 0) {
            first = tmp.getNext();
            size--;
            return;
        }

        if (index >= size) {
            throw new ArrayIndexOutOfBoundsException("index outofBounds");
        }
        int i = 0;
        while (i < j) {
            tmp = tmp.getNext();
            i++;
        }

        Node<T> del = tmp.getNext();
        tmp.setNext(del.getNext());
        size--;
    }

    public T remove(T t) {
        Node<T> tmp = first;
        if (tmp.getT().equals(t)) {
            first = first.getNext();
            size--;
            return tmp.getT();
        }

        Node<T> next;
        while (null != (next = tmp.getNext())) {
            if (next.getT().equals(t)) {
                tmp.setNext(next.getNext());
                size--;
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

    public int size() {
        return size;
    }

    public void out() {
        Node<T> tmp = first;
        do {
            System.out.println(tmp.getT());
        }  while (null != (tmp = tmp.getNext()));

        System.out.println("size:" + size);
    }

    public Node<T> reverseListNode() {
        Node<T> newNode = null;
        Node<T> curNode = first;

        while (null != curNode) {
            Node<T> nextNode = curNode.getNext(); // 2, 3,4,5
            curNode.setNext(newNode); // 1
            newNode = curNode; // 1
            curNode = nextNode; // 2,3,4,5
        }

        return first = newNode;
    }

}
