package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *   归并排序 --- 我的智商收到了极大地侮辱  一天没写出来
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/21 19:27
 **/
public class MergeSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8,7, 5, 11, 4, 3, 2, 1, 0};

    int loopCount = 0;

    int changeCount = 0;


    @Test
    public void mergeSort() {

        int[] tmp = new int[args.length];
        sort(args, tmp);
        logger.info("{}", tmp);
    }


    private void sort(int[] args, int[] tmp) {
        sort(0, args.length - 1, args, tmp, "首次触发");
    }


    private void sort(int left, int right, int[] args, int[] tmp, String source) {
        if (left < right) {
            int mid = (left + right) / 2;
            sort(left, mid, args, tmp, "left");
            sort(mid + 1, right, args, tmp, "right");
            merge(args, left, mid, right, tmp);
            logger.info("递归:{}, left:{}, mid:{}, right:{}",source, left, mid, right);
        }

    }

    private void merge(int[] args, int left, int mid, int right, int[] tmp) {
        int l = left;
        int r = mid + 1;
        int t = 0;
        while (l <= mid && r <= right) {
            if (args[l] <= args[r]) {
                tmp[t++] = args[l++];
            } else {
                tmp[t++] = args[r++];
            }
        }

        ///将左边剩余元素填充进tmp中
        while (l <= mid) {
            tmp[t++] = args[l++];
        }

        // 将右序列剩余元素填充进tmp中
        while (r <= right) {
            tmp[t++] = args[r++];
        }

        t = 0;
        while(left <= right){
            args[left++] = tmp[t++];
        }



    }


}
