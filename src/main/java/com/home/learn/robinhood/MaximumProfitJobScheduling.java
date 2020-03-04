package com.home.learn.robinhood;

import java.util.Arrays;
import java.util.Comparator;

public class MaximumProfitJobScheduling {
    public int jobScheduling(int[] startTime, int[] endTime, int[] profit) {
        int n = profit.length;
        Job[] jobs = new Job[n];
        for (int i = 0; i < n; i++) {
            jobs[i] = (new Job(startTime[i], endTime[i], profit[i]));
        }
        int[] dp = new int[jobs.length];
        Arrays.sort(jobs, Comparator.comparingInt(a -> a.end));
        Arrays.sort(endTime);
        dp[0] = jobs[0].profit;
        int result = 0;
        for (int i = 1; i < n; i++){
            int idx = Arrays.binarySearch(endTime, jobs[i].start);
            if(idx < 0) {
                idx = Math.abs(idx) - 2;
            }
            if(idx >= 0) {
                dp[i] = dp[idx];
            }
            dp[i] += jobs[i].profit;
            dp[i] = Math.max(dp[i], dp[i - 1]);
            result = Math.max(result, dp[i]);
        }
        return result;
    }

    static class Job {
        int start, end, profit;
        public Job(int s, int e, int p) {
            this.start = s;
            this.end = e;
            this.profit = p;
        }
    }
}
