package org.example.leetcode;



import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;

/**
 * <pre>
 *  给定一个整数数组 nums 和一个目标值 target，请你在该数组中找出和为目标值的那 两个 整数，并返回他们的数组下标。
 *
 * 你可以假设每种输入只会对应一个答案。但是，数组中同一个元素不能使用两遍。
 *
 * 来源：力扣（LeetCode）
 * 链接：https://leetcode-cn.com/problems/two-sum
 * 著作权归领扣网络所有。商业转载请联系官方授权，非商业转载请注明出处。
 * </pre>
 * @author 杨帮东
 * @since 1.0
 * @date 2020/04/29 16:59
 **/
public class twoSum {

    public void test() {
		int[] nums = {3,2,4};
		int target = 6;

		System.out.println(twoSum(nums, target));

    }

	public int[] twoSum(int[] nums, int target) {
		Map<Integer, Integer> map = new HashMap<>();
		for (int i = 0;i <nums.length;i++) {
			int z = nums[i];
			int t = target - z;
			Integer index1 = map.get(t);

			if (null != index1) {
				return new int[] {index1, i};
			}
			map.put(z, i);
		}
		return new int[]{};
	}

	// a b c d e a b c d e n a f g z s q a
	public int lengthOfLongestSubstring(String s) {
		int max = 0;
		int left = 0;
		int arraylen = s.length();
		HashMap<Character, Integer> map = new HashMap<>();
		char[] chars = s.toCharArray();
		for (int i = 1; i<= arraylen;i++) {
			char c = chars[i];
			if (map.containsKey(c)) {
				left = Math.max(left, map.get(c) + 1);
			}

			map.put(chars[i], i);
		}
		return max;
	}

}
