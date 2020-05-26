package org.example.sort;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * <pre>
 *     冒泡排序
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/20 11:27
 **/
public class BubbleSort {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private final int[] args = {8,7, 5, 11, 4, 3, 2, 1, 0};

    int loopCount = 0;

    int changeCount = 0;

    public int manYiYouXuDu() {
        int lenght = args.length;
        int num = lenght * (lenght - 1) / 2;
        logger.error("满意有序度:{}",  num);
        return num;
    }

    public int youxudu() {
        int num = 0;
        for (int i = 0; i < args.length;i++) {
            for (int j = 0;j < args.length;j++) {
                if (args[i] <= args[j] && i < j) {
                    num++;
                }
            }
        }
        logger.error("当前有序度为:{}",  num);
        return num;
    }

    /** 满意有序度: n*(n-1)/2
     *  逆序度 = 满有序度 - 有序度 (也就是冒泡排序需要交换的次数)
     *  有序元素对：a[i] <= a[j], 如果i < j。
     *      例如:6,5,1,2,3
     *      有序度为: 2
     *      满意有序度: 10
     * @author 杨帮东
     * @since 1.0
     * @date 2020/4/20 14:32
     * @return void
     */
    // 45 66
    @Test
    public void maoPao() {
        int i1 = manYiYouXuDu();
        int i2 = youxudu();
        logger.error("需交换次数:{}", i1 - i2);
        for (int i = 0,size = args.length;i < size; i++) {
            loopCount++;
            for (int j = i + 1; j < size; j++) {
                int ii = args[i];
                int jj = args[j];
                if (ii > jj) {
                    args[i] = jj;
                    args[j] = ii;
                    changeCount += 2;
                }
                loopCount++;
            }
        }


        logger.error("排序完成:{}", args);
        logger.error("实际循环次数:{}", loopCount);
        logger.error("实际交换次数:{}", changeCount);
    }
}
