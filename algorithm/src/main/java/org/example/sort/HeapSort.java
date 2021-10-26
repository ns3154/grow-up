package org.example.sort;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *      堆
 *      节点查找
 *          父节点 parent(i) = floor((i - 1)/2)
 *          左子节点 left(i)   = 2i + 1
 *          右又节点 right(i)  = 2i + 2
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/10/26 22:42
 **/
public class HeapSort {

    private static Logger logger = LoggerFactory.getLogger(HeapSort.class);
    // 非堆数组
    private static final int[] NO_HEAP_ARGS = {1, 2, 3, 4, 5, 6, 7, 8, 9};

    public static void main(String[] args) {
        int heapSize = 0;
        int[] arr = new int[NO_HEAP_ARGS.length];
        for (int noHeapArg : NO_HEAP_ARGS) {
            arr[heapSize++] = noHeapArg;
            heapInsert(arr,  heapSize - 1);
        }

        logger.info("args:{}", arr);
        removeHeadNode(arr);
        logger.info("args:{}", arr);

    }

    // 上浮
    private static void heapInsert (int[] args, int index) {
        int parentIndex = (index - 1) / 2;

        while (index > 0 && args[parentIndex] < args[index]) {
            swap(args, parentIndex, index);
            heapInsert(args, parentIndex);
        }
    }

    private static void down(int[] args, int index, int heapSize) {
        if (index > heapSize) {
            return;
        }
        int left = index * 2 + 1;
        int right = left + 1;
        int lag = right > heapSize ? left : right;
        if (lag < heapSize && args[index] < args[lag]) {
            swap(args, index, lag);
            down(args, lag, heapSize);
        }

    }

    private static void removeHeadNode(int[] args) {
        // 收尾互换
        swap(args, 0, args.length - 1);
        down(args, 0, args.length - 2);
    }


    private static void swap(int[] args, int j, int k) {
        int t = args[j];
        args[j] = args[k];
        args[k] = t;
    }

}
