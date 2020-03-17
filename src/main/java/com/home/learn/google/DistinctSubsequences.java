package com.home.learn.google;

import java.util.Arrays;

public class DistinctSubsequences {
    public int numDistinct(String s, String t) {
        int m = s.length(), n = t.length();
        int[][] dp = new int[n + 1][m + 1];
        Arrays.fill(dp[0], 1);
        for(int i = 1; i <= n; i++) {
            for(int j = 1; j <= m; j++) {
                dp[i][j] = dp[i][j - 1] + ((t.charAt(i - 1) == s.charAt(j - 1)) ? dp[i - 1][j - 1] : 0);
            }
        }
        return dp[n][m];
    }
}
