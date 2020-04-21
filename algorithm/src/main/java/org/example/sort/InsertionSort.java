package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     插入排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 14:58
 **/
public class InsertionSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8, 7, 5, 6, 4, 3, 2, 1, 0};

    @Test
    public void sort() {
        int length = args.length;
        for (int i = 1; i < length; i++) {
            if (args[i] < args[i - 1]) {
                int tmp = args[i];
                int j = i - 1;
                for (;j >= 0;j--) {
                    if (args[j] > tmp) {
                        args[j + 1] = args[j];
                    } else {
                        break;
                    }
                }
                args[j + 1] = tmp;
            }
        }
        logger.info("排序后:{}", args);
    }


}
