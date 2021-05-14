package com.home.learn.lyft;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;

public class MergeInterval {
    public int[][] merge(int[][] intervals) {
        Arrays.sort(intervals, Comparator.comparingInt(a -> a[0]));
        List<int[]> merged = new ArrayList<>();
        for (int[] interval : intervals) {
            if (merged.isEmpty() || merged.get(merged.size() - 1)[1] < interval[0]) {
                merged.add(interval);
            }
            else {
                merged.get(merged.size() - 1)[1] = Math.max(merged.get(merged.size() - 1)[1], interval[1]);
            }
        }
        return merged.toArray(new int[merged.size()][]);
    }

    public int[][] mergeV2(int[][] intervals) {
        // sort start&end
        int n = intervals.length;
        int[] start = new int[n];
        int[] end = new int[n];
        for(int i = 0; i < n; i++){
            start[i] = intervals[i][0];
            end[i] = intervals[i][1];
        }
        Arrays.sort(start);
        Arrays.sort(end);
        int st = 0;
        List<int[]> list = new ArrayList<>();
        for(int i = 0; i < n; i++){
            if(i == n - 1 || start[i + 1] > end[i]){
                int[] t = {start[st], end[i]};
                st = i + 1;
                list.add(t);
            }
        }
        return list.toArray(new int[list.size()][2]);
    }
}
