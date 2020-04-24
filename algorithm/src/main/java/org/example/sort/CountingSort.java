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
        logger.info("{}", args);
        // 将数组的参数作为 c数组的下标,多个相同则++
        for (int arg : args) {
            c[arg]++;
        }

        logger.info("c:{}", c);
        // 计算 a[i] 比 a[i-1] 多出多少
        for (int i = 1; i <= max; i++) {
            c[i] = c[i - 1] + c[i];
        }
        int[] newArray = new int[args.length];

        logger.info("c:{}", c);

        for (int i = args.length - 1; i >= 0; --i) {
            int index = c[args[i]] - 1;
            newArray[index] = args[i];
            c[args[i]]--;
        }

//
//        int index = 0;
//        for (int i = 0;i < c.length;i++) {
//            int tmp = c[i];
//            while (tmp > 0) {
//                newArray[index++] = i;
//                tmp--;
//            }
//        }
        logger.info("{}", newArray);

    }


}
