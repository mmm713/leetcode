package com.home.learn.leetcode;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BestMeetingPoint {
    public int minTotalDistance(int[][] grid) {
        List<Integer> rows = new ArrayList<>();
        List<Integer> cols = new ArrayList<>();
        for(int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                rows.add(i);
                cols.add(j);
            }
        }
        return minTotalDistance(rows) + minTotalDistance(cols);
    }

    private int minTotalDistance(List<Integer> list) {
        int res = 0;
        Collections.sort(list);
        for (int i = 0; i < list.size() / 2; i++) {
            res += list.get(list.size() - 1 - i) - list.get(i);
        }
        return res;
    }
}
