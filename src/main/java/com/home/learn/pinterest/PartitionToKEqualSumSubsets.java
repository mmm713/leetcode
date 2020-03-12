package com.home.learn.pinterest;

import java.util.Arrays;

public class PartitionToKEqualSumSubsets {
    public boolean canPartitionKSubsets(int[] nums, int k) {
        if (k < 1) {
            return false;
        }
        int sum = Arrays.stream(nums).sum();
        if (sum % k != 0) {
            return false;
        }
        int avg = sum / k;
        for (int n : nums) {
            if (n > avg) {
                return false;
            }
        }
        return backtrack(0, 0, nums, k, new boolean[nums.length], avg);
    }

    private boolean backtrack(int start, int sum, int[] nums, int k, boolean[] visited, int avg) {
        if (sum > avg) {
            return false;
        }
        if (k == 1) {
            return true;
        }
        if (sum == avg) {
            return backtrack(0, 0, nums, k -1, visited, avg);
        }
        for (int i = start; i < nums.length; i ++) {
            if (!visited[i] && sum + nums[i] <= avg) {
                visited[i] = true;
                if (backtrack(i + 1, sum + nums[i], nums, k, visited, avg)) {
                    return true;
                }
                visited[i] = false;
            }
        }
        return false;
    }
}
