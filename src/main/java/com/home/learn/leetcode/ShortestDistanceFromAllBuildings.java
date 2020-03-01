package com.home.learn.leetcode;

import java.util.ArrayDeque;
import java.util.Queue;

public class ShortestDistanceFromAllBuildings {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};
    int minDist;

    public int shortestDistance(int[][] grid) {
        int rows = grid.length;
        int cols = grid[0].length;
        int[][] dist = new int[rows][cols];
        minDist = Integer.MAX_VALUE;
        int reachableCell = 0;
        for (int r = 0; r < rows; r++) {
            for (int c = 0; c < cols; c++) {
                if (grid[r][c] == 1) {
                    updateDistToEachCell(grid, r, c, dist, reachableCell);
                    reachableCell--;
                }
            }
        }

        return minDist == Integer.MAX_VALUE ? -1 : minDist; // found cell(s) reachable from all buildings
    }

    void updateDistToEachCell(int[][] grid, int buildR, int buildC, int[][] dist, int reachableCell) {
        Queue<int[]> visited = new ArrayDeque<>();
        visited.offer(new int[] {buildR, buildC} );
        int level = 0;
        minDist = Integer.MAX_VALUE;
        while (!visited.isEmpty()) {
            level++;
            int levelSize = visited.size();
            for (int i = 0; i < levelSize; i++) {
                int[] cell = visited.poll();
                for (int[] direction : directions) {
                    int nextRow = cell[0] + direction[0];
                    int nextCol = cell[1] + direction[1];
                    if (withinBounds(nextRow, nextCol, grid) && grid[nextRow][nextCol] == reachableCell) {
                        grid[nextRow][nextCol]--;
                        dist[nextRow][nextCol] = dist[nextRow][nextCol] + level;
                        minDist = Math.min(minDist, dist[nextRow][nextCol]);
                        visited.offer(new int[] {nextRow, nextCol} );
                    }
                }
            }
        }
    }

    private boolean withinBounds(int row, int col, int[][] grid) {
        return row >= 0 && row < grid.length && col >= 0 && col < grid[0].length;
    }
}
