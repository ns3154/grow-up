package com.leet.code.com.leet.easy;

import org.junit.jupiter.api.Test;

public class MaxProfit {

    @Test
    public void run () {
        int[] nums = new int[] {7,1,5,3,6,4};
        System.out.println(maxProfitV2(nums));

    }

    public int maxProfitV2(int[] prices) {
        int max = 0;
        int min = prices[0];

        for (int i = 1; i < prices.length; i++) {
            int cur = prices[i];

            max = Math.max(max, cur - min);


            if (cur < min) {
                min = prices[i];
            }

        }

        return max;
    }


    public int maxProfit(int[] prices) {
        int max = 0;

        int left = -1;
        int right = 0;

        for (int i = 0; i < prices.length; i++) {

            int iCur = prices[i];
            if (iCur >= left && left > -1) {
                continue;
            }
            left = iCur;
            for (int j = i + 1; j < prices.length; j++) {
                int jCur = prices[j];
                int cur = jCur - iCur;
                max = Math.max(max, cur);
                if (jCur <= right) {
                    continue;
                }
                right = jCur;
            }
        }

        return max;
    }


}
