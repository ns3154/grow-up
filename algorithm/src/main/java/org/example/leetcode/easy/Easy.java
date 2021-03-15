package org.example.leetcode.easy;

import org.junit.jupiter.api.Test;

import java.util.*;

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
	public void fengzhi() {
		int[] a = {3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,3,7, 4};
		System.out.println(fengzhi(a, 0, a.length - 1));


	}

	public int fengzhi(int[] nums, int left, int right) {
		while (left < right) {
			int mid = left + ((right - left) >> 1);
			if (nums[mid + 1] > nums[mid]) {
				left = mid + 1;
			} else if (nums[mid + 1] == nums[mid]){
//				int a = fengzhi(nums, left, mid - 1);
//				int b = fengzhi(nums, mid + 1, right);
//				return Math.max(a, b);
				if (nums[left] < nums[right]) {
					left++;
				} else {
					right--;
				}
			}
			else {
				right = mid;
			}
		}
		return nums[left];
	}

	@Test
	public void mergeArray() {
		int[] n_n = {1,2,3,0,0,0};
		int[] m_m = {2,5,6};

		int n = 3;
		int m = 3;

		while (n > 0 && m > 0) {
			n_n[n + m - 1] = n_n[n - 1] > m_m[m - 1] ? n_n[--n] : m_m[--m];
		}

		while (m > 0) {
			n_n[--m] = m_m[m];
		}

		Arrays.stream(n_n).forEach(i -> System.out.print(i+ ","));
	}

	@Test
	public void kuohao() {
		String s = "{[}]";
		s = "(){}}{";
		if (null == s) {
			return;
		}
		char[] chars = s.toCharArray();
		if (chars.length % 2 == 1) {
			return;
		}

		Map<Character, Character> map = new HashMap<>();
		map.put(')', '(');
		map.put('}', '{');
		map.put(']', '[');
		if (map.containsKey(chars[0])) {
			return;
		}
		Stack<Character> stack = new Stack<>();
		for (int i = 0; i < chars.length; i++) {
			char c = chars[i];
			if (map.containsKey(c)) {
				if (stack.isEmpty()) {
					return;
				}
				Character character;
				if ((character = map.get(c)) != null && !Objects.equals(character, stack.peek())) {
					return;
				}
				stack.pop();
			} else {
				stack.push(c);
			}

		}

		System.out.println(stack.isEmpty());

	}

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

	@Test
	public void longestCommonPrefix() {
		String[] strs = {"flower","flow","1flight"};
		String preStr = strs[0];
		for (int i = 1; i < strs.length;i++) {
			preStr = longestCommonPrefix(preStr,strs[i]);
			if (preStr.length() == 0) {
				break;
			}
		}

		System.out.println(preStr);
	}

	@Test
	public void myAtoi() {

	}

	@Test
	public void maxArea() {
    	int[] height = {4,3,2,1,4};
    	int l = 0;
    	int r = height.length - 1;
    	int max = 0;
    	while (l < r) {
    		int m = Math.min(height[l], height[r]) * (r - l);
		    max = Math.max(max, m);
			if (height[l] < height[r]) {
				l++;
			} else {
				r--;
			}
	    }
		System.out.println(max);
	}

	@Test
	public void threeSum() {
    	// -4 -1 -1 0 1 2
		// -2 0 1 1 2
		// 0 0 0 0
    	int[] nums = {-2,0,1,1,2};
    	List<List<Integer>> list = new ArrayList<>();
    	if (null == nums || nums.length < 3) {
    		return;
	    }

		int i = 0;
    	int left = 1;
    	int right = nums.length - 1;
		Arrays.sort(nums);

		while (i < right && left < right && nums[right] >= 0) {
			int z = nums[i] + nums[left] + nums[right];
			List<Integer> l = new ArrayList<>();
			if(z == 0) {
				l.add(nums[i]);
				l.add(nums[left]);
				l.add(nums[right]);
				left = i + 1;
				right--;
				list.add(l);
				continue;
			}
			if (left < right - 1) {
				left++;
				continue;
			}
			left = ++i + 1;
		}

    	System.out.println(nums);


	}

	public String longestCommonPrefix(String s1, String s2) {
    	int len = Math.min(s1.length(), s2.length());
    	int index = 0;
    	while (len > index && s1.charAt(index) == s2.charAt(index)) {
    		index++;
	    }
		return s1.substring(0, index);
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
