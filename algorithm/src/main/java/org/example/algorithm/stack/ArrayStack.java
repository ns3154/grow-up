package org.example.algorithm.stack;

/**
 * <pre>
 *
 * </pre>
 *
 * @author 杨帮东 (qq:397827222)
 * @version 1.0
 * @date 2020/04/19 17:09
 **/
public class ArrayStack<T> {

    private Object[] array;

    private int count;

    private int size;

    public ArrayStack(int capacity) {
        this.array = new Object[capacity];
        size = capacity;
    }

    public void push(T str) {
        if (count == size -1) {
            throw new ArrayIndexOutOfBoundsException("满了");
        }
        array[count++] = str;
    }

    @SuppressWarnings("unchecked")
    public T pop() {
        if (count == 0) {
            return null;
        }
        return (T) array[--count];
    }

    public int size() {
        return count;
    }

}
