package com.home.learn.facebook;

import java.util.Arrays;
import java.util.Comparator;

public class NonOverlappingIntervals {
    //根据结尾排序后，晚结束的人如果头不重合，则有效，如果有重合，举例可证明贪婪最优
    public int eraseOverlapIntervals(int[][] intervals) {
        if (intervals.length == 0) {
            return 0;
        }
        Arrays.sort(intervals, Comparator.comparing(o -> o[1]));
        int end = intervals[0][1];
        int count = 1;
        for (int i = 1; i < intervals.length; i++) {
            if (intervals[i][0] >= end) {
                end = intervals[i][1];
                count++;
            }
        }
        return intervals.length - count;
    }
}
