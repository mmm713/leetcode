package com.home.learn.facebook;

import java.util.*;

public class TheSkylineProblem {
    public List<List<Integer>> getSkyline(int[][] buildings) {
        int[][] left = new int[buildings.length][2];
        int[][] right = new int[buildings.length][2];
        int k = 0;
        for (int[] b : buildings) {
            left[k] = new int[]{b[0], b[2]};
            right[k++] = new int[]{b[1], b[2]};
        }
        Arrays.sort(left, (m, n) -> m[0] == n[0] ? n[1] - m[1] : m[0] - n[0]);
        Arrays.sort(right, (m, n) -> m[0] == n[0] ? m[1] - n[1] : m[0] - n[0]);
        List<List<Integer>> res = new ArrayList<>();
        int h = 0;
        TreeMap<Integer, Integer> map = new TreeMap<>();
        map.put(0, 1);
        for (int i = 0, j = 0; j < k; ) {
            int cmp = i == k ? 1 : left[i][0] - right[j][0];
            if (cmp <= 0) {
                int x = left[i][0];
                map.put(left[i][1], map.getOrDefault(left[i][1], 0) + 1);
                int nh = map.lastKey();
                if (nh > h) {
                    res.add(Arrays.asList(x, nh));
                    h = nh;
                }
                i++;
            }
            else {
                int x = right[j][0];
                if (map.get(right[j][1]) == 1) {
                    map.remove(right[j][1]);
                }
                else {
                    map.put(right[j][1], map.getOrDefault(right[j][1], 0) - 1);
                }
                int nh = map.lastKey();
                if (nh < h) {
                    res.add(Arrays.asList(x, nh));
                    h = nh;
                }
                j++;
            }
        }
        return res;
    }
}
