package com.home.learn.facebook;

import java.util.Arrays;

public class StringCompressionDelete {
    public int getLengthOfOptimalCompression(String s, int K) {
        int[][] dp = new int[s.length()][K + 1];
        for (int i = 0; i < s.length(); i++) {
            Arrays.fill(dp[i], Integer.MAX_VALUE / 2);
        }
        char[]  cArr = s.toCharArray();
        return helper(0, K, dp, cArr);
    }

    private int helper(int i, int k, int[][] dp, char[] cArr) {
        if (k < 0) {
            return Integer.MAX_VALUE / 2;
        }
        if (i + k >= cArr.length) {
            return 0;
        }
        if (dp[i][k] != Integer.MAX_VALUE / 2) {
            return dp[i][k];
        }
        int ans = helper(i + 1, k - 1, dp, cArr); // discard
        // keep
        int len = 0;
        int same = 0;
        int diff = 0;
        for (int j = i; j < cArr.length && diff <= k; j++) {
            if (cArr[j] == cArr[i]) {
                ++same;
                if (same <= 2 || same ==10 || same == 100) {
                    ++len;
                }
            } else {
                ++diff;
            }
            ans = Math.min(ans, len + helper(j + 1, k - diff, dp, cArr));
        }
        dp[i][k] = ans;
        return ans;
    }
}
