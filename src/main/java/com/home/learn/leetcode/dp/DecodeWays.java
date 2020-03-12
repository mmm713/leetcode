package com.home.learn.leetcode.dp;

public class DecodeWays {
    public int numDecodings(String s) {
        int[] dp = new int[s.length()];
        if(s.length() <= 0 || s.charAt(0) == '0') return 0;
        dp[0] = 1;
        for(int i = 1; i < s.length(); i++) {
            boolean isEncoded = isEncode(s.substring(i - 1, i + 1));
            if (s.charAt(i) == '0') {
                if (!isEncoded) return 0;
                else dp[i] = i < 2 ? 1 : dp[i - 2];
            } else {
                if(isEncoded) {
                    dp[i] = (i < 2 ? 1 : dp[i - 2]) + dp[i - 1];
                } else {
                    dp[i] = dp[i - 1];
                }
            }
        }
        return dp[s.length() - 1];
    }
    private boolean isEncode(String s) {
        if(s.charAt(0) == '1') {
            return s.charAt(1) >= '0' && s.charAt(1) <= '9';
        }
        else if( s.charAt(0) =='2' ) {
            return s.charAt(1) >= '0' && s.charAt(1) <= '6';
        }
        return false;
    }
}
