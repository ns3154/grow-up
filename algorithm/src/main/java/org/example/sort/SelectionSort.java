package org.example.sort;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *      选择排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/21 14:21
 **/
public class SelectionSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8, 7, 5, 6, 4, 3, 2, 1, 0};

    int loopCount = 0;

    int changeCount = 0;

    //45 18
    public void sort() {
        for (int i = 0, size = args.length; i < size; i++) {
            loopCount++;
            int minIndex = i;
            for (int j =  i + 1; j < size; j++) {
                loopCount++;
                if ( args[minIndex] > args[j]) {
                    minIndex = j;
                }
            }
            int tmp = args[i];
            args[i] = args[minIndex];
            args[minIndex] = tmp;
            changeCount += 2;
        }
        logger.error("排序后:{}", args);
        logger.error("循环次数:{}", loopCount);
        logger.error("交换次数:{}", changeCount);
    }
}
