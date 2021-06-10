package com.home.learn.uber;

import java.util.HashMap;
import java.util.Map;

public class LongestArithmeticSubsequenceGivenDifference {
    public int longestSubsequence(int[] arr, int difference) {
        Map<Integer, Integer> map = new HashMap<Integer, Integer>();
        int max = 0;
        for (int j : arr) {
            int pre = j - difference;
            int count = map.getOrDefault(pre, 0) + 1;
            max = Math.max(max, count);
            map.put(j, count);
        }
        return max;
    }
}
