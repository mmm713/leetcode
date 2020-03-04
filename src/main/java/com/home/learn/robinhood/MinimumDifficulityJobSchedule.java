package com.home.learn.robinhood;

import java.util.Arrays;

public class MinimumDifficulityJobSchedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        int[][] dp = new int[d][n];
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mx = Math.max(jobDifficulty[i], mx);
            dp[0][i] = mx;
        }
        for (int i = 1; i < d; i++) {
            for (int j = i; j < n; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                for (int r = j; r >= i; r--) {
                    max = Math.max(max, jobDifficulty[r]);
                    min = Math.min(dp[i-1][r-1] + max, min);
                }
                dp[i][j] = min;
                System.out.println("-------- for i : " + i + " and j : " + j +" -------");
                System.out.println(Arrays.deepToString(dp).replace("], ", "]\n").replace("[[", "[").replace("]]", "]"));
            }
        }
        return dp[d-1][n-1];
    }
}
