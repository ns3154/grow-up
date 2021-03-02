package org.example.leetcode.easy;

import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
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

	@Test
	public void lengthOfLongestSubstring1() {
		String s = "pwwkew";
		char[] chars = s.toCharArray();
		int[] nums = new int[128];
		int max = 0;
		for (int start = 0, end = 0, length = chars.length; end < length; end++) {
			char c = chars[end];
			start = Math.max(nums[c], start);
			nums[c] = end + 1;
			max = Math.max(max, end - start + 1);
		}
		System.out.println(max);
	}

	@Test
	public void romanToInt() {
		String s = "MCMXCIV";
		int sum = 0;
		int preNum = getValue(s.charAt(0));
		for(int i = 1;i < s.length(); i ++) {
			int num = getValue(s.charAt(i));
			if(preNum < num) {
				sum -= preNum;
			} else {
				sum += preNum;
			}
			preNum = num;
		}
		sum += preNum;
		System.out.println(sum);
	}

	private int getValue(char ch) {
		switch(ch) {
			case 'I': return 1;
			case 'V': return 5;
			case 'X': return 10;
			case 'L': return 50;
			case 'C': return 100;
			case 'D': return 500;
			case 'M': return 1000;
			default: return 0;
		}
	}
}
