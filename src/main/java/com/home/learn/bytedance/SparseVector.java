package com.home.learn.bytedance;

import java.util.HashMap;
import java.util.Map;

public class SparseVector {
    public Map<Integer, Integer> map = new HashMap<>();
    SparseVector(int[] nums) {
        for(int i = 0; i < nums.length; i++) {
            if(nums[i] != 0) {
                map.put(i, nums[i]);
            }
        }
    }

    // Return the dotProduct of two sparse vectors
    public int dotProduct(SparseVector vec) {
        Map<Integer, Integer> a, b;
        if(map.size() < vec.map.size()) {
            a = map;
            b = vec.map;
        } else {
            a = vec.map;
            b = map;
        }
        int res = 0;
        for(Integer key : a.keySet()) {
            if(b.containsKey(key)) {
                res += a.get(key) * b.get(key);
            }
        }
        return res;
    }
}
