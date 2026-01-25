package org.example.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *      希尔排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/21 16:08
 **/
public class ShellSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8,7, 5, 11, 4, 3, 2, 1, 0};

    int loopCount = 0;

    int changeCount = 0;

    // 32 18
    public void sort() {
        // 4 2 1
        int length = args.length;
        int gap = length;
        while ((gap /= 2) > 0) {
            loopCount++;
            for (int j = 0; j < length - gap;j++) {
                loopCount++;
                int z = j + gap;
                while (z < length && args[z] < args[j]) {
                    int tmp = args[j];
                    args[j] = args[z];
                    args[z] = tmp;
                    changeCount += 2;
                    z += gap;
                    loopCount++;
                }
            }

        }

        logger.error("排序后:{}", args);
        logger.error("循环次数:{}", loopCount);
        logger.error("交换次数:{}", changeCount);
    }
}
