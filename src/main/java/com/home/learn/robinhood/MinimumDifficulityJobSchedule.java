package com.home.learn.robinhood;

import java.util.Arrays;

public class MinimumDifficulityJobSchedule {
    public int minDifficulty(int[] jobDifficulty, int d) {
        int n = jobDifficulty.length;
        if (n < d) {
            return -1;
        }
        //记录第 d 天，做完 n 个工作的耗时
        int[][] dp = new int[d][n];
        int mx = Integer.MIN_VALUE;
        for (int i = 0; i < n; i++) {
            mx = Math.max(jobDifficulty[i], mx);
            dp[0][i] = mx;
        }
        for (int i = 1; i < d; i++) {
            //起点为i，因为之前每天至少要做一个工作
            for (int j = i; j < n; j++) {
                int max = Integer.MIN_VALUE;
                int min = Integer.MAX_VALUE;
                //从后向前遍历，方便找到切割点之后的最大值
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
