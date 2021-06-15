package com.home.learn.google;

public class MagicSquaresInGrid {
    public int numMagicSquaresInside(int[][] grid) {
        int m = grid.length, n = grid[0].length, res = 0;
        for (int i = 0; i < m - 2; ++i) {
            for (int j = 0; j < n - 2; ++j) {
                if (grid[i + 1][j + 1] == 5 && isValid(grid, i, j)) ++res;
            }
        }
        return res;
    }

    //那么其实这个神奇正方形的各行各列及对角线之和就已经被限定了，必须是15才行，而且最中间的位置必须是5，否则根本无法组成满足要求的正方形
    private boolean isValid(int[][] grid, int i, int j) {
        int[] cnt = new int[10];
        for (int x = i; x < i + 2; ++x) {
            for (int y = j; y < j + 2; ++y) {
                int k = grid[x][y];
                if (k < 1 || k > 9 || cnt[k] == 1) return false;
                cnt[k] = 1;
            }
        }
        if (5 != grid[i + 1][j + 1]) return false;
        if (15 != grid[i][j] + grid[i][j + 1] + grid[i][j + 2]) return false;
        if (15 != grid[i + 1][j] + grid[i + 1][j + 1] + grid[i + 1][j + 2]) return false;
        if (15 != grid[i + 2][j] + grid[i + 2][j + 1] + grid[i + 2][j + 2]) return false;
        if (15 != grid[i][j] + grid[i + 1][j] + grid[i + 2][j]) return false;
        if (15 != grid[i][j + 1] + grid[i + 1][j + 1] + grid[i + 2][j + 1]) return false;
        if (15 != grid[i][j + 2] + grid[i + 1][j + 2] + grid[i + 2][j + 2]) return false;
        if (15 != grid[i][j] + grid[i + 1][j + 1] + grid[i + 2][j + 2]) return false;
        return 15 == grid[i + 2][j] + grid[i + 1][j + 1] + grid[i][j + 2];
    }
}
