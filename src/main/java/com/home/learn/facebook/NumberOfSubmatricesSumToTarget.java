package com.home.learn.facebook;

import java.util.HashMap;
import java.util.Map;

public class NumberOfSubmatricesSumToTarget {
    public int numSubmatrixSumTarget(int[][] matrix, int target) {
        int m = matrix.length;
        int n = matrix[0].length;
        int res = 0;
        Map<Integer, Integer> map = new HashMap<>();

        for (int k = 0; k < n; k++) {
            int[] sum = new int[m];
            for (int j = k; j < n; j++) {
                map.clear();
                int s = 0;
                map.put(0, 1);
                for (int i = 0; i < m; i++) {
                    sum[i] += matrix[i][j];
                    s += sum[i];
                    if (map.containsKey(s - target)) {
                        res += map.get(s - target);
                    }
                    map.put(s, map.getOrDefault(s, 0) + 1);
                }
            }
        }
        return res;
    }
}
