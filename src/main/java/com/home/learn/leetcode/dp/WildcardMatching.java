package com.home.learn.leetcode.dp;

public class WildcardMatching {
    public boolean isMatch(String s, String p) {
        int si = 0, pi = 0, sstar = -1, pstar = -1;
        char[] sc = s.toCharArray();
        char[] pc = p.toCharArray();
        while(si < sc.length) {
            if(pi < p.length() && (sc[si] == pc[pi] || pc[pi] == '?')) {
                si++;
                pi++;
            } else if (pi < p.length() && pc[pi] == '*') {
                pstar = pi++;
                sstar = si;
            } else if (pstar > -1) {
                pi = pstar + 1;
                si = ++sstar;
            } else return false;
        }
        while (pi < pc.length && pc[pi] == '*') ++pi;
        return (pi == pc.length);
    }

    public boolean isMatchRecursive(String s, String p) {
        if (p.isEmpty()) {
            return s.isEmpty();
        }
        if (s.isEmpty()) {
            int i = 0;
            while(i < p.length() && p.charAt(i) == '*') {
                i++;
            }
            return i == p.length();
        }
        if (p.charAt(0) == '*') {
            return isMatch(s, p.substring(1)) || isMatch(s.substring(1), p);
        } else {
            return matchSingle(s, p, 0, 0) && isMatch(s.substring(1), p.substring(1));
        }
    }

    public boolean isMatchDp(String s, String p) {
        int m = s.length(), n = p.length();
        boolean[][] dp = new boolean[m + 1][n + 1];
        dp[0][0] = true;
        for (int i = 0; i <= m; ++i) {
            for (int j = 1; j <= n; ++j) {
                //i-1 and j-1 is the current position
                if (p.charAt(j - 1) == '*') {
                    dp[i][j] = dp[i][j - 1] || (i > 0 && dp[i - 1][j]);
                } else {
                    dp[i][j] = matchSingle(s, p, i - 1, j - 1) && dp[i - 1][j - 1];
                }
            }
        }
        return dp[m][n];
    }


    private boolean matchSingle(String s, String p, int i, int j) {
        return !s.isEmpty() && (i >= 0) && (j >= 0) && (s.charAt(i) == p.charAt(j) || p.charAt(j) == '?');
    }
}
