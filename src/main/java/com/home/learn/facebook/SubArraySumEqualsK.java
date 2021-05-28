package com.home.learn.facebook;

import java.util.HashMap;
import java.util.Map;

public class SubArraySumEqualsK {
    public int subArraySum(int[] nums, int k) {
        int[] sum = new int[nums.length + 1];
        for(int i = 0; i < nums.length; i++) {
            sum[i + 1] = sum[i] + nums[i];
        }
        Map<Integer, Integer> map = new HashMap<>();
        int res = 0;
        for (int j : sum) {
            if (map.containsKey(j - k)) {
                res += map.get(j - k);
            }
            map.put(j, map.getOrDefault(j, 0) + 1);
        }
        return res;
    }

    //if all positive
    public boolean subarraySumPositive(int[] nums, int k) {
        int start = -1;
        int sum = 0;
        for (int num : nums) {
            sum += num;
            if (sum == k) {
                return true;
            } else if (sum > k) {
                while (start < k) {
                    sum -= nums[++start];
                    if (sum == k) {
                        return true;
                    } else if (sum < k) {
                        break;
                    }
                }
            }
        }
        return false;
    }
}
