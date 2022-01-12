package org.example.leetcode;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.function.BiFunction;

/**
 * 字符串分割
 *  https://leetcode-cn.com/problems/number-of-good-ways-to-split-a-string/
 *
 * @author 杨帮东
 * @date 2022-01-12
 */
public class NumSplits {


    @Test
    public void test () {
        // 4
        String str = "acaba";
        System.out.println(numSplitsV1(str));
        System.out.println(numSplitsV2(str));

    }

    /**
     * 滑动窗口 第一个版本
     *
     * @param s 年代
     * @return int
     */
    public int numSplitsV1(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int res = 0;

        HashMap<Character, Integer> right = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char ch = chars[i];
            right.compute(ch, (k, v) -> null == v ? 1 : v + 1);
        }

        HashMap<Character, Integer> left = new HashMap<>();
        for (int i = 0; i < length; i++) {
            char ch = chars[i];
            left.compute(ch, (k, v) -> null == v ? 1 : v + 1);

            Integer v = right.get(ch);
            if (null != v) {
                v = v - 1;
                if (v > 0) {
                    right.put(ch, v);
                } else {
                    right.remove(ch);
                }
            }

            res += right.size() == left.size() ? 1 : 0;
        }

        return res;
    }

    /**
     * 滑动窗口 V2版本
     *
     * @param s
     * @return int
     */
    public int numSplitsV2(String s) {
        char[] chars = s.toCharArray();
        int length = chars.length;
        int res = 0;

        int[] right = new int[256];
        int rightC = 0;
        for (int i = 0; i < length; i++) {
            char ch = chars[i];
            rightC += right[ch] == 0 ? 1 : 0;
            right[ch]++;
        }

        int leftC = 0;
        int[] left = new int[256];
        for (int i = 0; i < length; i++) {
            char ch = chars[i];
            if (left[ch] == 0) {
                leftC++;
            }
            if (--right[ch] == 0) {
                rightC--;
            }
            left[ch]++;
            res += leftC == rightC ? 1 : 0;
        }
        return res;
    }
}
