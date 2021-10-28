package com.home.learn.smartnews;

import java.util.Comparator;
import java.util.PriorityQueue;

public class NumberOfWaysToArriveAtDestination {
    public int countPaths(int n, int[][] roads) {
        PriorityQueue<int[]> q = new PriorityQueue<>(Comparator.comparing(ints -> ints[1]));
        boolean[] visited = new boolean[roads.length];
        int[] ways = new int[roads.length];
        int[] init = {0, 0, 0};
        int cost = Integer.MAX_VALUE;
        q.offer(init);
        while(!q.isEmpty()) {
            int[] curr = q.poll();
            if(curr[1] > cost) {
                break;
            }
            visited[curr[0]] = true;
        }
        return ways[roads.length - 1];
    }
}
