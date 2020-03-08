package com.home.learn.wish;

public class MinimumPathSum {
    public int minPathSum(int[][] grid) {
        final int m = grid.length;
        final int n = grid[0].length;
        int[] result = new int[n];
        result[0] = grid[0][0];
        for (int i = 1; i < n; i++)
            result[i] = grid[0][i] + result[i - 1];
        for (int i = 1; i < m; i++) {
            result[0] += grid[i][0];
            for (int j = 1; j < n; j++)
                result[j] = Math.min(result[j - 1], result[j]) + grid[i][j];
        }
        return result[n - 1];
    }
}
