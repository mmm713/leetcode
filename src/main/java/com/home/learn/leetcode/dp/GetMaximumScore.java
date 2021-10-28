package com.home.learn.leetcode.dp;

public class GetMaximumScore {
    private static final int mod = 1000000007;
    public int maxSum(int[] nums1, int[] nums2) {
        int m = nums1.length;
        int n = nums2.length;
        long best1 = 0, best2 = 0;
        int i = 0, j = 0;
        while (i < m || j < n) {
            if (i < m && j < n) {
                if (nums1[i] < nums2[j]) {
                    best1 += nums1[i];
                    ++i;
                } else if (nums1[i] > nums2[j]) {
                    best2 += nums2[j];
                    ++j;
                } else {
                    long best = Math.max(best1, best2) + nums1[i];
                    best1 = best2 = best;
                    ++i;
                    ++j;
                }
            } else if (i < m) {
                best1 += nums1[i];
                ++i;
            } else {
                best2 += nums2[j];
                ++j;
            }
        }
        return (int) (Math.max(best1, best2) % mod);
    }
}
