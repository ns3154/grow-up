package org.example.leetcode.lcp;


/**
 * https://leetcode-cn.com/problems/ccw6C7/
 * 排列组合
 *
 * @author 杨帮东
 * @date 2022-01-10
 */
public class PaintingPlan {

    public void paintingPlan() {
//        System.out.println("n = 2, k = 2, 结果应为: 4, 程序输出: " + paintingPlan(2, 2));
//        System.out.println("n = 2, k = 1, 结果应为: 0, 程序输出:" + paintingPlan(2, 1));
//        System.out.println("n = 2, k = 4, 结果应为: 1, 程序输出:" + paintingPlan(2, 4));
        System.out.println("n = 4, k = 13, 结果应为: 32, 程序输出:" + paintingPlan(4, 13));
    }



    public int paintingPlan(int n, int k) {

        if (k == 0) {
            return 1;
        }
        if (n * n < k || k < n) {
            return 0;
        }

        if (n * n == k) {
            return 1;
        }

        int res = 0;

        for (int i = 0; i <= n; i++) {
            for (int j = 0; j <= n; j++) {
                if ((i * n + j * n) - (i * j) == k) {
                    res += c(i, n) * c(j, n);
                }
            }
        }

        return res;
    }

    public int c(int t, int n) {
        if (t == 0) {
            return 1;
        }

        int res = 1;

        for (int i = 0; i < t; i++) {
            res *= n--;
        }

        for (int i = t; i > 1; i--) {
            res /= i;
        }

        return res;
    }


}
