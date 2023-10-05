package com.home.learn.bytedance;

import java.util.Arrays;

public class MinimumTimeToCompleteTrips {
    public long minimumTime(int[] time, int totalTrips) {
        long l = Arrays.stream(time).min().getAsInt();
        long r = ((long) Arrays.stream(time).max().getAsInt()) * totalTrips;
        while(l < r) {
            long m = l + (r - l) / 2;
            long tot = Arrays.stream(time).mapToLong(t -> m / t).sum();
            if(tot < totalTrips) {
                l = m + 1;
            } else {
                r = m;
            }
        }
        return l;
    }
}
