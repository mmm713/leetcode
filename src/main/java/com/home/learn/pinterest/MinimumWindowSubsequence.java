package com.home.learn.pinterest;

import java.util.Arrays;

import static com.home.learn.Helpers.print1D;
import static com.home.learn.Helpers.print2D;

public class MinimumWindowSubsequence {
    public String minWindow(String S, String T) {
        int m = S.length(), n = T.length(), start = -1, window = Integer.MAX_VALUE;
        int[][] dp = new int[m + 1][n + 1];
         for(int i = 0; i <= m; i++) {
             for(int j = 0; j <= n; j++) {
                 dp[i][j] = (j == 0) ? i : -1;
             }
         }
        for(int i = 1; i <= m; i++) {
            for (int j = 1; j <= Math.min(i, n); j++) {
                dp[i][j] = (S.charAt(i - 1) == T.charAt(j - 1)) ? dp[i - 1][j - 1] : dp[i - 1][j];
            }
            if(dp[i][n] != -1) {
                if(window > i - dp[i][n]) {
                    window = i - dp[i][n];
                    start = dp[i][n];
                }
            }
        }
        print2D(dp);
        return window == Integer.MAX_VALUE ? "" : S.substring(start, start + window);
    }

    public String minWindowII(String S, String T) {
        int m = S.length(), n = T.length(), start = -1, window = Integer.MAX_VALUE;
        int[] dp = new int[n + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        print1D(dp);
        for(int i = 1; i <= m; i++) {
            for (int j = Math.min(i, n); j > 0; j--) {
                dp[j] = (S.charAt(i - 1) == T.charAt(j - 1)) ? dp[j - 1] : dp[j];
            }
            dp[0] = i;
            print1D(dp);
            if(dp[n] != -1) {
                if(window > i - dp[n]) {
                    window = i - dp[n];
                    start = dp[n];
                }
            }
        }
        return window == Integer.MAX_VALUE ? "" : S.substring(start, start + window);
    }
}
