package com.home.learn.facebook;

import java.util.List;

public class MaximumDistanceInArrays {
    public int maxDistance(List<List<Integer>> arrays) {
        int min = arrays.get(0).get(0);
        int max = arrays.get(0).get(arrays.get(0).size()-1);
        int res = 0;
        for (int i = 1; i < arrays.size(); i++) {
            List<Integer> arr = arrays.get(i);

            int lo = arr.get(0);
            int hi = arr.get(arr.size() - 1);

            res = Math.max(res, max - lo);
            res = Math.max(res, hi - min);
            min = Math.min(min, lo);
            max = Math.max(max, hi);
        }
        return res;
    }
}
