package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     计数排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/24 17:34
 **/
public class CountingSort {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {2, 5, 3, 0, 2, 3, 0, 3};


    @Test
    public void sort() {
        int max = args[0];
        int length = args.length;
        while (length > 0) {
            if (max < args[--length]) {
                max = args[length];
            }
        }
        int[] c = new int[max + 1];
        logger.error("待排序数据:{}", args);
        // 将数组的参数作为 c数组的下标,多个相同则++
        for (int arg : args) {
            c[arg]++;
        }
        logger.error("以数据为下标,并计算个数,新数组:c:{}", c);
        // 计算 a[i] 比 a[i-1] 多出多少 包涵自身
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }
        logger.error("计算 下标n 比 n-1 多多少:{}", c);

        int[] newArray = new int[args.length];

        int le = args.length;
        while (--le >= 0) {
            int t = c[args[le]] -1;
            newArray[t] = args[le];
            c[args[le]]--;
        }

        logger.error("排序后数据:{}", newArray);

    }


}
