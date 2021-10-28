package com.home.learn.doordash;

public class NumSubarrayBoundedMax {
    public int numSubarrayBoundedMax(int[] nums, int left, int right) {
        if (nums == null) {
            return 0;
        }
        int res = 0, temp = 0, prev = 0;
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] < left) {
                res += temp;
            } else if (nums[i] > right) {
                temp = 0;
                prev = i + 1;
            } else {
                temp = i - prev + 1;
                res += temp;
            }
        }
        return res;
    }
}
