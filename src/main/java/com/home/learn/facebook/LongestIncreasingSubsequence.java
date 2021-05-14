package com.home.learn.facebook;

import java.util.Arrays;

public class LongestIncreasingSubsequence {
    public int lengthOfLIS(int[] nums) {
        int[] dp = new int[nums.length];
        int len = 0;
        for (int num : nums) {
            int i = Arrays.binarySearch(dp, 0, len, num);
            if (i < 0) {
                i = -(i + 1);
            }
            dp[i] = num;
            if (i == len) {
                len++;
            }
        }
        return len;
    }


    public int findNumberOfLIS(int[] nums) {
        int res = 0, mx = 0, n = nums.length;
        int[] len = new int[n];
        int[] cnt = new int[n];
        Arrays.fill(len, 1);
        Arrays.fill(cnt, 1);
        for (int i = 0; i < n; ++i) {
            for (int j = 0; j < i; ++j) {
                if (nums[i] <= nums[j]) continue;
                if (len[i] == len[j] + 1) {
                    cnt[i] += cnt[j];
                } else if (len[i] < len[j] + 1) {
                    len[i] = len[j] + 1;
                    cnt[i] = cnt[j];
                }
            }
            if (mx == len[i]) res += cnt[i];
            else if (mx < len[i]) {
                mx = len[i];
                res = cnt[i];
            }
        }
        return res;
    }
}
