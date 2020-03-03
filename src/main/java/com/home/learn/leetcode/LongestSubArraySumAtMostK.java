package com.home.learn.leetcode;

public class LongestSubArraySumAtMostK {
    public int atMostSum(int[] arr, int n, int k) {
        int sum = 0;
        int cnt = 0, maxcnt = 0;
        for (int i = 0; i < n; i++) {
            if ((sum + arr[i]) <= k) {
                sum += arr[i];
                cnt++;
            } else if(sum != 0) {
                sum = sum - arr[i - cnt] + arr[i];
            }
            maxcnt = Math.max(cnt, maxcnt);
        }
        return maxcnt;
    }
}
