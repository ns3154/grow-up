package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *      基数排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/26 11:18
 **/
public class RadixSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {15, 29, 26, 16, 15, 10, 13, 28, 13, 17, 29, 14, 19, 22, 25};

    private final int bucket_size = 10;

    @Test
    public void sort() {
        int[] array = args;
        logger.error("原数组:{}", array);
        int maxValue = maxValue(array);
        int digit = maxDigit(maxValue);

        for (int t = 1; t <= digit;t++) {
            int[] bucket = new int[bucket_size];
            int length = array.length;
            while (--length >= 0) {
                int index = getBucketIndex(array[length], t);
                bucket[index] += 1;
            }

            for (int i = 1; i < bucket_size; i++) {
                bucket[i] = bucket[i] + bucket[i - 1];
            }

            logger.error("计算后结果:{}", bucket);

            int[] newArray = new int[array.length];

            for (int i = array.length - 1; i >= 0; i--) {
                int r = getBucketIndex(array[i], t);
                int index = bucket[r] - 1;
                newArray[index] = array[i];
                bucket[r]--;
            }

            array = newArray;
            logger.error("{}", array);
        }




    }

    // 判断当前值是几位数
    private int maxDigit(int maxValue) {
        if (maxValue == 0) {
            return -1;
        }
        int lenght = 0;
        for (long temp = maxValue; temp != 0; temp /= 10) {
            lenght++;
        }
        return lenght;
    }

    // 获取最大数
    private int maxValue(int[] args) {
        int maxValue = args[0];
        int le = args.length;
        while (--le >= 0) {
            if (maxValue < args[le]) {
                maxValue = args[le];
            }
        }
        return maxValue;
    }

    private int getBucketIndex(int i, int digit) {
        int index = -1;
        switch (digit) {
            case 1 : index = i % 10; break;
            case 2 : index = i / 10 % 10;break;
            case 3 : index = i / 100 % 10;break;
            case 4 : index = i / 1000 % 10;break;
            default:
                break;
        }
        return index;
    }

    @Test
    public void text() {
        System.out.println(getBucketIndex(12345, 1));
        System.out.println(getBucketIndex(12345, 2));
        System.out.println(getBucketIndex(12345, 3));
        System.out.println(getBucketIndex(12345, 4));
    }
}
