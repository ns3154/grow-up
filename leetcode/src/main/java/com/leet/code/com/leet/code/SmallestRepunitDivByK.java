package com.leet.code.com.leet.code;


/**
 * <p>
 * https://leetcode.cn/problems/smallest-integer-divisible-by-k/
 *
 *
 * 给定正整数 k ，你需要找出可以被 k 整除的、仅包含数字 1 的最 小 正整数 n 的长度。
 *
 * 返回 n 的长度。如果不存在这样的 n ，就返回-1。
 *
 * 1 <= k <= 10 5
 * maxK = 100000 --> 99999
 * </p>
 * 111 3
 * @author 杨帮东
 * @version 1.0
 * @date 2022/07/13 12:28
 **/
public class SmallestRepunitDivByK {

    public void run () {
        int k = 23;
        int len = smallestRepunitDivByK(k);
        System.out.println(len);

    }



    public int smallestRepunitDivByK(int k) {
        if (k % 2 == 0 || k % 5 == 0) {
            return -1;
        }


        long s = 1;
        int len = 1;
        while (s % k != 0) {
            s = s % k;
            s = s * 10 + 1;
            len++;
        }
        return len;
    }
}
