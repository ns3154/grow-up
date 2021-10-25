package org.example.sort;

import com.google.common.collect.PeekingIterator;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.atomic.AtomicInteger;

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

    AtomicInteger loopCount = new AtomicInteger(0);

    AtomicInteger changeCount = new AtomicInteger(0);

    @Test
    public void sort() {
        int length = args.length;
        AtomicInteger counts = new AtomicInteger(0);
        quickSort(args, 0, length - 1, "开始", counts);
        logger.error("{}", args);
        logger.error("循环次数:{}, 交换次数:{}", loopCount.get(), changeCount.get());
    }
    private void quickSort(int[] args, int l, int r, String source, AtomicInteger counts) {
        logger.error("当前source:{}, left:{}, right:{}, lenght:{}, counts:{}", source, l, r, args.length,
                counts.getAndIncrement());
        int start = partition(args, l, r, source);

        if (l < start - 1) {
            quickSort(args, l, start - 1, "left", counts);
        }
        if (start + 1 < r) {
            quickSort(args, start + 1, r, "right", counts);
        }
    }
    private int partition(int[] args, int l, int r, String source) {
        logger.warn("处理:{}", source);
        int start = l;
        int end = r;
        int tmp = args[l];

        while (start < end) {
            // 找出 右边 小于 tmp 的 下标
            while (tmp <= args[end] && start < end) {
                end--;
                loopCount.incrementAndGet();
            }

            // 从左边递增 找出大于tmp的下标
            while (tmp >= args[start] && start < end) {
                start++;
                loopCount.incrementAndGet();
            }
            // 数据交换
            int t = args[start];
            args[start] = args[end];
            args[end] = t;
            loopCount.incrementAndGet();
            changeCount.addAndGet(2);
        }

        // 基准数 调换
        args[l] = args[start];
        args[start] = tmp;
        changeCount.addAndGet(2);
        return start;
    }

    @Test
    public void sort1() {
        quickSort1(args, 0, args.length - 1);
    }


    public void quickSort1(int[] arrs, int left, int right) {
        int index = left + (int) (Math.random() * (right - left + 1));
        int finalP = partition(args, left, right, index);
        quickSort1(arrs, left, finalP);
        quickSort1(arrs, finalP + 1, right);

    }

    private int partition(int[] args, int left, int right, int index) {
        int tmp = args[index];
        args[index] = args[right];
        args[right] = tmp;

        while (left < right) {

            while (args[right] > tmp) {
                right--;
            }

            while (args[left] < tmp) {
                left++;
            }

            int t = args[right];
            args[left]

        }


        return 0;
    }






}
