package com.home.learn.leetcode.dp;

public class RegularExpressionMatching {
    public boolean isMatch(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //i-1 and j-1 is the current position
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 2] || (matchSingle(s, p, i - 1, j - 2) && dp[i - 1][j]);
                } else {
                    dp[i][j] = matchSingle(s, p, i - 1, j - 1) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }

    public boolean isMatchRec(String s, String p) {
        if (p.isEmpty()) return s.isEmpty();
        if (p.length() > 1 && p.charAt(1) == '*') {
            return isMatch(s, p.substring(2)) || (matchSingle(s, p, 0, 0) && isMatch(s.substring(1), p));
        } else {
            return matchSingle(s, p, 0, 0) && isMatch(s.substring(1), p.substring(1));
        }
    }

    private boolean matchSingle(String s, String p, int i, int j) {
        return !s.isEmpty() && (i >= 0) && (j >= 0) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '.');
    }
}
