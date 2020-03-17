package com.home.learn.google;

public class MaximumCardScores {

    /*
    一组牌->array 正整数。 两个人轮流拿牌，每人每轮可以且必须拿1， 2， 或 3 张牌。直到牌堆拿完为止。最后score是拿到牌的数值之和。问如何最大化score
     */
    public int MaximumCardScores(int[] input) {
        if(input == null || input.length == 0) return 0;
        int[] dp = new int[input.length];
        for(int i = 0; i < Math.min(input.length, 3); i++) {
            dp[i] = i == 0 ? input[i] : input[i] + dp[i - 1];
        }
        if(input.length > 3) {
            dp[3] = input[3] + dp[2];
            for(int i = 4; i < input.length; i++) {
                dp[i] = input[i] + Math.max(dp[i - 2], Math.max(dp[i - 3], dp[i - 4]));
            }
        }
        return dp[input.length - 1];
    }
}
