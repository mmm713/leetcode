package com.home.learn.microsoft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PalindromePartition {
    public List<List<String>> partition(String s) {
        List<List<String>> list = new ArrayList<>();
        backtrack(list, new ArrayList<>(), s, 0);
        return list;
    }

    private void backtrack(List<List<String>> list, List<String> tempList, String s, int start){
        if(start == s.length())
            list.add(new ArrayList<>(tempList));
        else{
            for(int i = start; i < s.length(); i++){
                if(isPalindrome(s, start, i)){
                    tempList.add(s.substring(start, i + 1));
                    backtrack(list, tempList, s, i + 1);
                    tempList.remove(tempList.size() - 1);
                }
            }
        }
    }

    private boolean isPalindrome(String s, int low, int high){
        while(low < high) {
            if (s.charAt(low++) != s.charAt(high--)) return false;
        }
        return true;
    }

    public int minCut(String s) {
        int n = s.length();
        char[] sc = s.toCharArray();
        boolean[][] p = new boolean[n][n];
        int[] dp = new int[n];
        for(int i = 0; i < n; i++) {
            dp[i] = i;
            for(int j = 0; j <= i; j++) {
                if(sc[i] == sc[j] && (i - j < 2 || p[j + 1][i - 1])) {
                    p[j][i] = true;
                    dp[i] = (j == 0) ? 0 : Math.min(dp[i], dp[j - 1] + 1);
                }
            }
        }
        return dp[n - 1];
    }

    public boolean checkPartitioning(String s) {
        int N = s.length();
        char[] A = s.toCharArray();
        // build dp table
        boolean[][] dp = new boolean[N][N];
        for (int i = N - 1; i >= 0; --i) {
            for (int j = i; j < N; ++j) {
                if (A[i] == A[j]) dp[i][j] = (i + 1 > j - 1 || dp[i + 1][j - 1]);
                else dp[i][j] = false;
            }
        }
        // iterate every mid and then check: left, mid and right
        for (int i = 1; i < N - 1; ++i) {
            for (int j = i; j < N - 1; ++j) {
                if (dp[0][i - 1] && dp[i][j] && dp[j + 1][N - 1]) return true;
            }
        }
        return false;
    }

    public int palindromePartition(String str, int k) {
        int n = str.length();
        int[][] conNeeded = new int[n][n];
        for (int g = 1; g < n; g++) {
            for (int i = 0, j = g; j < n; i++, j++) {
                conNeeded[i][j] = conNeeded[i + 1][j - 1];
                if (str.charAt(i) != str.charAt(j)) {
                    conNeeded[i][j]++;
                }
            }
        }
        int[][] dp = new int[n][k + 1];
        for (int[] dpArr : dp) {
            Arrays.fill(dpArr, -1);
        }
        return minConversions(0, k, dp, conNeeded, n);
    }

    private int minConversions(int idx, int k, int[][] dp, int[][] conNeeded, int n) {
        if (dp[idx][k] != -1) {
            return dp[idx][k];
        }
        if (k == 1) {
            return dp[idx][k] = conNeeded[idx][n - 1];
        }
        int min = Integer.MAX_VALUE;
        int cut = idx + 1;
        while (n - cut >= k - 1) {
            min = Math.min(min, minConversions(cut, k - 1, dp, conNeeded, n) + conNeeded[idx][cut - 1]);
            cut++;
        }
        return dp[idx][k] = min;
    }
}
