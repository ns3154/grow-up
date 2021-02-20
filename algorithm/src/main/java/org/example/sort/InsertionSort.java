package org.example.sort;

import org.junit.jupiter.api.Test;
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

    int loopCount = 0;

    int changeCount = 0;

    // 44 33
    @Test
    public void sortFor() {
        int length = args.length;
        for (int i = 1; i < length; i++) {
            loopCount++;
            if (args[i] < args[i - 1]) {
                int tmp = args[i];
                int j = i - 1;
                for (; j >= 0; j--) {
                    loopCount++;
                    if (args[j] > tmp) {
                        args[j + 1] = args[j];
                        changeCount++;
                    } else {
                        break;
                    }
                }
                args[j + 1] = tmp;
                changeCount++;
            }
        }
        logger.error("排序后:{}", args);
        logger.error("循环次数:{}", loopCount);
        logger.error("交换次数:{}", changeCount);
    }

    @Test
    public void sortWhile() {
        int length = args.length;
        for (int x = 1; x < length; x++) {
            int tmp = args[x];
            int y = x - 1;

            while (y >= 0) {
                loopCount++;
                if (tmp < args[y]) {
                    args[y + 1] = args[y];
                    changeCount++;
                    y--;
                } else {
                    break;
                }
            }
            args[y + 1] = tmp;
            changeCount++;
        }
        logger.error("排序后:{}", args);
        logger.error("循环次数:{}", loopCount);
        logger.error("交换次数:{}", changeCount);
    }


}
