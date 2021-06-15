package com.home.learn.google;

import java.util.Comparator;
import java.util.PriorityQueue;

public class MinimumNumberRefuelingStops {
    //dp[i] 表示加了i次油能到达的最远距离
    public int minRefuelStops(int target, int startFuel, int[][] stations) {
        int N = stations.length;
        long[] dp = new long[N + 1];
        dp[0] = startFuel;
        for (int i = 0; i < N; ++i) {
            for (int t = i; t >= 0; --t) {
                if (dp[t] >= stations[i][0]) {
                    dp[t + 1] = Math.max(dp[t + 1], dp[t] + (long) stations[i][1]);
                }
            }
        }

        for (int i = 0; i <= N; ++i) {
            if (dp[i] >= target) return i;
        }
        return -1;
    }

    //此题贪婪可解
    public int minRefuelStopsHeap(int target, int startFuel, int[][] stations) {
        int res = 0, i = 0, n = stations.length;
        PriorityQueue<Integer> q = new PriorityQueue<>(Comparator.reverseOrder());
        for (; startFuel < target; res++) {
            while(i < n && stations[i][0] <= startFuel) {
                q.offer(stations[i++][1]);
            }
            if(q.isEmpty()) return -1;
            startFuel += q.poll();
        }
        return res;
    }
}
