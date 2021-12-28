package com.home.learn.leetcode.dp;

public class DecodeWays {
    public int numDecodings(String s) {
        // Base Case Check
        if (s == null || s.length() == 0 || s.charAt(0) == '0'){
            return 0;
        }
        // dp[i] = number of ways a string of lenght i can be decoded.
        char[] sc = s.toCharArray();
        int[] dp = new int[s.length() + 1];
        dp[0] = 1;
        dp[1] = 1;
        for (int i = 2 ; i <= s.length(); i++){
            // get last digit
            int oneDigit = sc[i - 1] - '0';
            // get last two digits
            int twoDigit = (sc[i - 2] - '0') * 10 + sc[i - 1] - '0';
            if (oneDigit >= 1){
                dp[i] += dp[i - 1];
            }
            if (twoDigit >= 10 && twoDigit <= 26){
                dp[i] += dp[i - 2];
            }
        }
        return dp[s.length()];
    }


    int M = 1000000007;
    //如果有*
    public int numDecodingsII(String s) {
        long[] dp = new long[s.length() + 1];
        dp[0] = 1;
        dp[1] = s.charAt(0) == '*' ? 9 : s.charAt(0) == '0' ? 0 : 1;
        for (int i = 1; i < s.length(); i++) {
            if (s.charAt(i) == '*') {
                dp[i + 1] = 9 * dp[i] % M;
                if (s.charAt(i - 1) == '1')
                    dp[i + 1] = (dp[i + 1] + 9 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '2')
                    dp[i + 1] = (dp[i + 1] + 6 * dp[i - 1]) % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + 15 * dp[i - 1]) % M;
            } else {
                dp[i + 1] = s.charAt(i) != '0' ? dp[i] : 0;
                if ((s.charAt(i - 1) == '1') || (s.charAt(i - 1) == '2' && s.charAt(i) <= '6'))
                    dp[i + 1] = dp[i + 1] + dp[i - 1] % M;
                else if (s.charAt(i - 1) == '*')
                    dp[i + 1] = (dp[i + 1] + (s.charAt(i) <= '6' ? 2 : 1) * dp[i - 1]) % M;
            }
        }
        return (int) dp[s.length()];
    }
}
