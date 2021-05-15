package com.home.learn.uber;

/*
个字符串，可以打乱顺序，问能否组成k个palindrome。也就是说，
abb和bab或者bba作为input，给出的结果应该一样（注意区分本题和狸寇德730，
后者说的是subsequence，也就是顺序不可打乱，因此难度增加）。例如，abracadabra, k = 2, 返回false。
 */
public class ComposeKPalindrome {
    public boolean canForm(String input, int k) {
        int[] hash = new int[26];
        int odd = 0;
        for(char c : input.toCharArray()) {
            if(++hash[c - 'a'] % 2 == 1) {
                odd++;
            } else {
                odd--;
            }
        }
        return k <= input.length() && k >= odd;
    }

    public int countPalindromicSubsequences(String s) {
        int len = s.length();
        int[][] dp = new int[len][len];

        char[] chs = s.toCharArray();
        for(int i = 0; i < len; i++){
            dp[i][i] = 1;// Consider the test case "a", "b" "c"...
        }

        for(int i = len - 1; i >= 0; i--) { //i = len - 2
            for(int j = i + 1; j < len; j++) {
                if(chs[i] == chs[j]){
                    int low = i + 1;
                    int high = j - 1;

                    /* Variable low and high here are used to get rid of the duplicate*/
                    while(low <= high && chs[low] != chs[j]){
                        low++;
                    }
                    while(low <= high && chs[high] != chs[j]){
                        high--;
                    }
                    if(low > high){
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 2;
                    }
                    else if(low == high){
                        dp[i][j] = dp[i + 1][j - 1] * 2 + 1;
                    }
                    else{
                        dp[i][j] = dp[i + 1][j - 1] * 2 - dp[low + 1][high - 1];
                    }
                }
                else{
                    dp[i][j] = dp[i][j - 1] + dp[i + 1][j] - dp[i + 1][j - 1];  //s.charAt(i) != s.charAt(j)
                }
                dp[i][j] = dp[i][j] < 0 ? dp[i][j] + 1000000007 : dp[i][j] % 1000000007;
            }
        }

        return dp[0][len - 1];
    }
}
