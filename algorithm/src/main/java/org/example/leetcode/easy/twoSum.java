package org.example.leetcode.easy;

import org.junit.Test;

import java.util.HashMap;
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

    @Test
    public void test() {
        System.out.println(reverse(-10));
    }

    public int reverse(int x) {
        int res = 0;
        while(x!=0) {
            int tmp = x%10;
            if (res>214748364 || (res==214748364 && tmp>7)) {
                return 0;
            }
            if (res<-214748364 || (res==-214748364 && tmp<-8)) {
                return 0;
            }
            res = res*10 + tmp;
            x /= 10;
        }
        return res;
    }
}
