package com.home.learn.facebook;

import java.util.HashSet;
import java.util.Set;

public class ContinuousSubarraySum {
    public boolean checkSubarraySum(int[] nums, int k) {
        int[] preSum = new int[nums.length + 1];
        preSum[1] = nums[0];
        for (int i = 1; i < nums.length; i++) {
            preSum[i + 1] = preSum[i] + nums[i];
            if (preSum[i] == 0 || (nums[i] == 0 && nums[i - 1] == 0)) {
                return true;
            }
            if (preSum[i] >= k) {
                for (int j = i - 2; j >= 0; j--) {
                    if ((preSum[i] - preSum[j]) % k == 0) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

    public boolean checkSubarraySumSet(int[] nums, int k) {
        Set<Integer> count = new HashSet<>();
        int sum = 0, pre = 0;
        for (int num : nums) {
            sum += num;
            int t = (k == 0) ? sum : (sum % k);
            if (count.contains(t)) return true;
            count.add(pre);
            pre = t;
        }
        return false;
    }
}
