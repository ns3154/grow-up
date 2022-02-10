package org.example.leetcode;

import org.junit.jupiter.api.Test;
import java.util.*;

/**
 * https://leetcode-cn.com/problems/Ygoe9J/
 *
 * @author 杨帮东
 * @date 2022-01-13
 */
public class CombinationSum {

    int count = 0;

    @Test
    public void test() {
        int[] candidates = {2, 3, 6, 7};
        int target = 7;
        List<List<Integer>> res = combinationSum(candidates, target);

        for (List<Integer> re : res) {
            System.out.print("[");
            for (Integer integer : re) {
                System.out.print(integer);
            }
            System.out.print("]");
            System.out.print("\n");
        }

        System.out.println("执行次数:" + count);
    }

    public List<List<Integer>> combinationSum(int[] candidates, int target) {
        List<List<Integer>> res = new ArrayList<>();
        dfs(0, 0, target, candidates, new ArrayList<>(), res);
        return res;
    }


    public void dfs (int index, int sum, int target, int[] candidates, List<Integer> list, List<List<Integer>> res) {
        count++;
        if (index >= candidates.length || sum > target) {
            return;
        }
        if (sum == target) {
            res.add(new ArrayList<>(list));
            return;
        }
        list.add(candidates[index]);
        dfs(index, sum + candidates[index], target, candidates, list, res);
        list.remove(list.size() - 1);
        dfs(index + 1, sum, target, candidates, list, res);
    }


}
