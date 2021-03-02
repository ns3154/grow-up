package org.example.leetcode.easy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

/**
 * <pre>
 *
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2021/03/02 19:23
 **/
public class Easy {


    @Test
    public void lengthOfLongestSubstring() {
        String s = "pwwkew";
        char[] chars = s.toCharArray();
        Map<Character, Integer> map = new HashMap<>();
        int max = 0;
        for (int startIndex = 0, endIndex = 0, len = chars.length; endIndex < len; endIndex++) {
            char c = chars[endIndex];
            if (map.containsKey(c)) {
                startIndex = Math.max(map.get(c), startIndex);
            }

            max = Math.max(max, endIndex - startIndex + 1);
            map.put(c, endIndex + 1);
        }
        System.out.println(max);
    }
}
