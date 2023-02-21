package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class ClimbStairs {

    @Test
    public void run () {
        System.out.println(climbStairs(4));
    }

    public int climbStairs(int n) {
        if (n == 0 || n == 1) {
            return n;
        }

        int[] dp = new int[n + 1];

        dp[1] = 1;
        dp[2] = 2;

        for (int i = 3; i <= n; i++) {
            dp[i] = dp[i - 1] + dp[i - 2];
        }
        return dp[n];
    }
}
