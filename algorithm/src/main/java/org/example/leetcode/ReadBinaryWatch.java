package org.example.leetcode;


import java.util.*;

/**
 * 读二进制表
 * https://leetcode-cn.com/problems/binary-watch/
 * @author 杨帮东
 * @date 2022-01-17
 */
public class ReadBinaryWatch {


    public void test() {
        List<String> strings = readBinaryWatchV1(3);
        strings.stream().forEach(System.out::println);
    }


    public List<String> readBinaryWatchV1(int turnedOn) {
        int[] arrays = {1, 2, 4, 8, 1, 2, 4, 8, 16, 32};
        List<String> res = new ArrayList<>();
        dfsV1(turnedOn, 0, 0, 0, arrays, res);
        return res;
    }

    private void dfsV1(int cur, int h, int m, int index, int[] arrays, List<String> res) {
        if (cur == 0) {
            res.add(h + ":" + (m < 10 ? "0" + m : m + ""));
            return;
        }
        for (int i = index; i < 10 && cur > 0; i++) {

            // H 计算
            if (i < 4 && h + arrays[i] < 12) {
                dfsV1(cur - 1, h + arrays[i], m, i + 1, arrays, res);
            }

            // M计算
            if (i >= 4 && m + arrays[i] < 60) {
                dfsV1(cur - 1, h, m + arrays[i], i + 1, arrays, res);
            }




        }
    }


}
