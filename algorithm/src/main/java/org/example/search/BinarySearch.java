package org.example.search;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *   二分查找
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/26 15:43
 **/
public class BinarySearch {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] array = {1,2,3,4,5,6,7,8,9,10};

    public void search() {
        int searchNum = 9;
        logger.error("待查找数组:{}, 目标数:{}", array, searchNum);

        int p = searchByLoop(array, searchNum);
        logger.error("searchByLoop下标:{}", p);

        p = searchByRecursion(array, searchNum, 0, array.length - 1, 0);
        logger.error("searchByRecursion下标:{}", p);

        p = searchLast(array, searchNum, 0, array.length - 1);
        logger.error("searchFirst下标:{}", p);

        p = searchFirst_1(array, searchNum, 0, array.length - 1);
        logger.error("searchFirst_1下标:{}", p);
    }

    public void search1() {
        for (int i : array) {
            logger.info("目标数:{}, 索引下标为:{}, 数组:{}", i, searchLoop(array, 0, array.length, i), array);
        }
        logger.info("目标数:{}, 索引下标为:{}, 数组:{}", 11, searchLoop(array, 0, array.length, 11), array);
        logger.info("目标数:{}, 索引下标为:{}, 数组:{}", 0, searchLoop(array, 0, array.length, 0), array);
    }

    public int searchLoop(int[] array, int left, int right, int target) {
        if (left > right - 1) {
            return -1;
        }
        int mid = (left + right) / 2;
        if (array[mid] == target) {
            return mid;
        }

        if(array[mid] > target) {
            return searchLoop(array, left, mid - 1, target);
        }

        if (array[mid] < target) {
            return searchLoop(array, mid + 1, right, target);
        }

        return -1;
    }


    private int searchByLoop(int[] array, int searchNum) {
        int left = 0;
        int right = array.length - 1;
        int count = 0;
        while (left <= right) {
            count++;
            int p =  (left + right) >> 1;
            if (array[p] == searchNum) {
                logger.error("执行次数:{}", count);
                logger.error("找到了,下标为:{}", p);
                return p;
            } else if (array[p] < searchNum) {
                left = p + 1;
            } else {
                right = p - 1;
            }
        }
        logger.error("执行次数:{}", count);
        return -1;
    }


    private int searchByRecursion(int[] array, int searchNum, int left, int right, int count) {
        if (left > right) {
            return - 1;
        }
        count++;
        int p =  (left + right) >> 1;
        if (array[p] == searchNum) {
            logger.error("执行次数:{}", count);
            logger.error("找到了,下标为:{}", p);
            return p;
        } else if (array[p] < searchNum) {
            left = p + 1;
        } else {
            right = p - 1;
        }
        logger.error("执行次数:{}", count);
        return searchByRecursion(array, searchNum, left, right, count);
    }

    /**
     * 查找第一个出现的目标值
     * @author 杨帮东
     * @since 1.0
     * @date 2020/4/26 17:15
     * @return int
     */
    private int searchLast(int[] array, int searchNum, int left, int right) {
        if (left > right) {
            return - 1;
        }
        int p =  (left + right) >> 1;


        if (array[p] > searchNum) {
            right = p - 1;
        } else if (array[p] < searchNum){
            left = p + 1;
        } else {
            if (array[p + 1] != searchNum || p == array.length - 1) {
                return p;
            } else {
                left = p + 1;
            }
        }
        return searchLast(array, searchNum, left, right);
    }

    private int searchFirst_1(int[] array, int searchNum, int left, int right) {
        if (left > right) {
            return - 1;
        }
        int p =  (left + right) >> 1;


        if (array[p] >= searchNum) {
            right = p - 1;
        } else {
            left = p + 1;
        }

        if(array[left] == searchNum) {
            return left;
        }
        return searchFirst_1(array, searchNum, left, right);
    }
}
