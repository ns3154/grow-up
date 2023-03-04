package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class MinMaxDifference {

    @Test
    public void run () {

        System.out.println(minMaxDifference(11891));

    }




    public int minMaxDifference(int num) {
        String numStr = String.valueOf(num);
        char cur = 'a';
        char[] chars = numStr.toCharArray();
        for (int i = 0; i < chars.length; i++) {
            char c = chars[i];
            if (c != '9' && cur == 'a') {
                cur = c;
                chars[i] = '9';
                continue;
            }

            if (cur == c) {
                chars[i] = '9';
            }
        }

        int max = Integer.parseInt(new String(chars));

        chars = numStr.toCharArray();
        cur = chars[0];
        chars[0] = '0';
        for (int i = 1; i < chars.length; i++) {
            if (cur == chars[i]) {
                chars[i] = '0';
            }
        }

        int min = Integer.parseInt(new String(chars));
        return max - min;
    }
}
