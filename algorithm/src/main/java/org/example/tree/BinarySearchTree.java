package org.example.tree;

import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/28 18:19
 **/
public class BinarySearchTree {

    private Node root;

    public BinarySearchTree(int index) {
        root = new Node(index);
    }

    public void insert(int value) {
        insert(value, root);
    }

    public Node find(int value) {
        Node current = root;

        while (null != current) {
            int currentValue = current.getCurrent();
            if (currentValue == value) {
                break;
            } else if (currentValue < value) {
                current = current.getRight();
            } else {
                current = current.getLeft();
            }
        }
        return current;
    }


    public void del(int value) {
        Node parentNode = root;
        Node delNode = root;
        int lr = 0;

        while (null != delNode && delNode.getCurrent() != value) {
            parentNode = delNode;
            if (value > delNode.getCurrent()) {
                delNode = delNode.getRight();
                lr = 1;
            } else {
                delNode = delNode.getLeft();
                lr = 0;
            }
        }

        if (null == delNode) {
            return;
        }

        if (null == delNode.getRight() && null == delNode.getLeft()) {
            if (lr == 1) {
                parentNode.setRight(null);
            } else {
                parentNode.setLeft(null);
            }
        }




    }

    /**
     * 前序遍历
     *
     * 对于树中的任意节点来说，先打印这个节点，然后再打印它的左子树，最后打印它的右子树。
     * @author 杨帮东
     * @param
     * @since 1.0
     * @date 2020/4/29 14:10
     * @return java.util.List<java.lang.Integer>
     * @throws
     */
    public List<String> preOrder() {
        List<String> list = new CopyOnWriteArrayList<>();
        Node current = root;
        preOrder(current, list, "根");
        return list;
    }


    /**
     * 中需遍历
     * 对于树中的任意节点来说，先打印它的左子树，然后再打印它本身，最后打印它的右子树。
     * @author 杨帮东
     * @param
     * @since 1.0
     * @date 2020/4/29 14:11
     * @return java.util.List<java.lang.Integer>
     * @throws
     */
    public List<Integer> inOrder() {
        List<Integer> list = new CopyOnWriteArrayList<>();
        inOrder(root, list);
        return list;
    }

    /**
     * 后序遍历
     * 对于树中的任意节点来说，先打印它的左子树，然后再打印它的右子树，最后打印这个节点本身。
     * @author 杨帮东
     * @param
     * @since 1.0
     * @date 2020/4/29 14:27
     * @return java.util.List<java.lang.Integer>
     * @throws
     */
    public List<Integer> postOrder() {

        List<Integer> list = new CopyOnWriteArrayList<>();
        postOrder(root, list);
        return list;
    }

    private void postOrder(Node root, List<Integer> list) {
        if (null != root) {
            postOrder(root.getLeft(), list);
            postOrder(root.getRight(), list);
            list.add(root.getCurrent());
        }
    }

    private void inOrder(Node root, List<Integer> list) {
        if (null != root) {
            inOrder(root.getLeft(), list);
            list.add(root.getCurrent());
            inOrder(root.getRight(), list);
        }
    }

    private void preOrder(Node node, List<String> list, String str) {
        if (null != node) {
            list.add(str + ":" + node.getCurrent());
            preOrder(node.getLeft(), list, "左");
            preOrder(node.getRight(), list, "右");
        }
    }

    private void insert(int value, Node node) {
        if(null == root) {
            root = new Node(value);
            return;
        }
        if (value < node.getCurrent()) {
            if (null == node.getLeft()) {
                node.setLeft(new Node(value));
                return;
            }
            insert(value, node.getLeft());
        } else {
            if (null == node.getRight()) {
                node.setRight(new Node(value));
                return;
            }
            insert(value, node.getRight());
        }
    }


}
