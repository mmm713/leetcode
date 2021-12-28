package com.home.learn.amazon;

public class BurstBallons {
    public int maxCoins(int[] nums) {
        int n = nums.length;
        int[][] dp = new int[n + 2][n + 2];
        int[] values = new int[n + 2];
        System.arraycopy(nums, 0, values, 1, n);
        values[0] = 1;
        values[n + 1] = 1;
        for (int i = n - 1; i >= 0; i--) {
            for (int j = i + 2; j < n + 2; j++) {
                for (int k = i + 1; k < j; k++) {
                    dp[i][j] = Math.max(dp[i][j], dp[i][k] + dp[k][j] + values[i] * values[k] * values[j]);
                }
            }
        }
        return dp[0][n + 1];
    }
}
