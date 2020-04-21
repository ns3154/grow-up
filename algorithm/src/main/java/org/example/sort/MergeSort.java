package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.logging.Level;

/**
 * <pre>
 *   归并排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/21 19:27
 **/
public class MergeSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8,7, 5, 11, 4, 3, 2, 9, 1,0};

    int loopCount = 0;

    int changeCount = 0;


    @Test
    public void mergeSort() {
        mergeSort(args);
    }

    private int[] mergeSort(int[] args) {
        if  (args.length < 2) {
            return args;
        }
        int length = args.length;
        int m = length / 2;
        int[] a1;
        int[] a2;
        a1 = new int[m];
        System.arraycopy(args, 0, a1, 0, m);
        if (length % 2 == 0) {
            a2 = new int[m];
            System.arraycopy(args, m, a2, 0, m);
        } else {
            a2 = new int[m + 1];
            System.arraycopy(args, m, a2, 0, m + 1);
        }

        return mergeSort(a1, a2);
    }

    public int[] mergeSort(int[] a1, int[] a2) {
        int[] a = mergeSort(a1);
        int[] b = mergeSort(a2);

        int[] ab = new int[a1.length + a2.length];
        if (a.length <= 2) {
            for (int i = 0;i<a.length;i++) {

            }
        }

        return mergeSort(a, b);
    }



}
