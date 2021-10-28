package org.example.sort;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.function.Supplier;

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

    public static void main (String[] args) {
        Heap heap = new Heap(NO_HEAP_ARGS.length);
        for (int noHeapArg : NO_HEAP_ARGS) {
            heap.append(noHeapArg);
        }
        logger.info("args:{}, count:{}", heap.arr, heap.count);
        heap.sort();
        logger.info("args:{}, count:{}", heap.arr, heap.count);
    }


    static class Heap {

        private int[] arr;

        private int count;

        public Heap() {
            this.count = 0;
        }

        public Heap(int size) {
            this();
            arr = new int[size];
        }

        public int[] arr () {
            return arr;
        }

        public int count() {
            return count;
        }

        public  void append (int param) {
            arr[count++] = param;
            shiftUp(count - 1);
        }

        public int maxNumber() {
            return arr[0];
        }

        public int[] sort() {
            int c = count;
            while (c > 0) {
                swap(0, --c);
                heapify(0, c);
            }
            return arr;
        }

        private void swap(int j, int k) {
            int tmp = arr[j];
            arr[j] = arr[k];
            arr[k] = tmp;
        }

        /**
         * 上浮
         *
         * @param index 指数
         */
        private  void shiftUp(int index) {
            if (index == 0) {
                return;
            }

            int parentIndex = parentIndex(index);
            while (arr[index] > arr[parentIndex]) {
                swap(index, parentIndex);
                shiftUp(parentIndex);
            }
        }

        /**
         * 堆化/下浮
         *
         * @param index 指数
         */
        private void heapify(int index, int c) {
            int left = childLeft(index);
            while (left < c) {
                int largest = left + 1 < c && arr[left] < arr[left + 1] ? left + 1 : left;
                largest = arr[index] < arr[largest] ? largest : index;
                if (largest == index) {
                    return;
                }
                swap(index, largest);
                heapify(largest, c);
            }
        }


        private int childLeft(int index) {
            return 2 * index + 1;
        }

        private int childRight(int index) {
            return childLeft(index) + 1;
        }

        private int parentIndex(int index) {
            return (index - 1) / 2;
        }

    }

}
