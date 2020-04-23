package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *      快排
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/22 18:45
 **/
public class QuickSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8, 7, 5, 11, 4, 3, 2, 1, 0};

    @Test
    public void sort() {
        int length = args.length;
        quickSort(args, 0 , length - 1);
        logger.info("{}", args);
    }

    private void quickSort(int[] args, int l, int r) {

        if (l > r) {
            return;
        }

        int sl = l;
        int sr = r;
        int tmp = args[l];

        while (sl < sr) {

            // 找出 右边 小于 tmp 的 下标
            while (tmp <= args[sr] && sl < sr) {
                sr--;
            }

            // 从左边递增 找出大于tmp的下标
            while (tmp >= args[sl] && sl < sr) {
                sl++;
            }
            // 数据交换
            int t = args[sl];
            args[sl] = args[sr];
            args[sr] = t;
        }

        // 基准数 调换
        args[l] = args[sl];
        args[sl] = tmp;

        quickSort(args, l, sl - 1);
        quickSort(args,  sr + 1, r);


    }



}
