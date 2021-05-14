package com.home.learn.facebook;

import java.util.HashMap;
import java.util.Map;

public class SubarraySumEqualsK {
    public int subarraySum(int[] nums, int k) {
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
}
