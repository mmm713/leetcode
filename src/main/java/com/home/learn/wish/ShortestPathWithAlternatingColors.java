package com.home.learn.wish;

import java.util.*;

public class ShortestPathWithAlternatingColors {
    public int[] shortestAlternatingPaths(int n, int[][] red_edges, int[][] blue_edges) {
        List<List<Integer>> rGraph = new ArrayList<>(n);
        List<List<Integer>> bGraph = new ArrayList<>(n);
        for (int i = 0; i < n; i++) {
            rGraph.add(new ArrayList<>());
            bGraph.add(new ArrayList<>());
        }
        for (int[] e : red_edges) rGraph.get(e[0]).add(e[1]);
        for (int[] e : blue_edges) bGraph.get(e[0]).add(e[1]);
        int[] result = new int[n];
        Arrays.fill(result, -1);
        int step = 0;
        //int[0] is current node, int[1] is next color
        Queue<int[]> q = new ArrayDeque<>();
        boolean[][] visited = new boolean[n][2];
        q.add(new int[]{0, 0});
        q.add(new int[]{0, 1});
        visited[0][0] = true;
        visited[0][1] = true;
        while (!q.isEmpty()) {
            int cnt = q.size();
            while(!q.isEmpty() && --cnt >= 0) {
                int[] a = q.poll();
                int idx = a[0];
                int color = a[1];
                if (result[idx] == -1) result[idx] = step;
                else result[idx] = Math.min(step, result[idx]);
                if (color == 0) {
                    for (int j : bGraph.get(idx)) {
                        if (!visited[j][1]) {
                            visited[j][1] = true;
                            q.add(new int[]{j, 1});
                        }
                    }
                } else {
                    for (int j : rGraph.get(idx)) {
                        if (!visited[j][0]) {
                            visited[j][0] = true;
                            q.add(new int[]{j, 0});
                        }
                    }
                }
            }
            step++;
        }
        return result;
    }
}
