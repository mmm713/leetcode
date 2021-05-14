package com.home.learn.facebook;

import java.util.Arrays;
import java.util.Stack;

public class IsGraphBipartite {

    public boolean isBipartite(int[][] graph) {
        int n = graph.length;
        int[] colors = new int[n];
        for (int i = 0; i < n; i++) {
            if (colors[i] == 0 && dfs(graph, colors, i, 1)) {
                return false;
            }
        }
        return true;
    }

    private boolean dfs(int[][] graph, int[] colors, int i, int color) {
        colors[i] = color;
        for (int j = 0; j < graph[i].length; j++) {
            int neigh = graph[i][j];
            if (colors[neigh] == -color) {
                continue;
            }
            if (colors[neigh] == color || dfs(graph, colors, neigh, -color)) {
                return true;
            }
        }
        return false;
    }


    public boolean isBipartiteBfs(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int start = 0; start < n; ++start) {
            if (color[start] == 0) {
                Stack<Integer> stack = new Stack<>();
                stack.push(start);
                color[start] = 1;
                while (!stack.empty()) {
                    Integer node = stack.pop();
                    for (int nei: graph[node]) {
                        if (color[nei] == 0) {
                            stack.push(nei);
                            color[nei] = -color[node];
                        } else if (color[nei] == color[node]) {
                            return false;
                        }
                    }
                }
            }
        }
        return true;
    }


    public boolean isBipartiteUF(int[][] graph) {
        int n = graph.length;
        int[] color = new int[n];
        for (int i = 0; i < n; ++i) {
            color[i] = i;
        }
        for (int i = 0; i < n; ++i) {
            if (graph[i].length == 0) continue;
            int x = find(color, i), y = find(color, graph[i][0]);
            if (x == y) return false;
            for (int j = 1; j < graph[i].length; ++j) {
                int parent = find(color, graph[i][j]);
                if (x == parent) return false;
                color[parent] = y;
            }
        }
        return true;
    }

    int find(int[] color, int i) {
        return color[i] == i ? i : find(color, color[i]);
    }
}
