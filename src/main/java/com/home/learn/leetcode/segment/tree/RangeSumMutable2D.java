package com.home.learn.leetcode.segment.tree;

import java.util.LinkedHashMap;
import java.util.Map;

public class RangeSumMutable2D {
    int[][] matrix;
    int[][] sum;  // DP matrix to store sum from (0, 0) to (i, j)
    Map<Integer, Integer> updates;  // update bucket, use HashMap to avoid updating on the same cell causing problem
    int updateLimit, m, n;

    public RangeSumMutable2D(int[][] matrix) {
        this.matrix = matrix;
        this.updates = new LinkedHashMap<>();
        this.m = matrix.length;
        this.n = (matrix.length == 0 ? 0 : matrix[0].length);
        this.sum = new int[m + 1][n + 1];
        for (int i = 0; i <= m; i++) {
            this.sum[i] = new int[n + 1];
        }
        calculateSum();
        updateLimit = (int) Math.sqrt(2 * m * n);
    }

    private void calculateSum() {
        for (int i = 0; i < m; i++) {
            for (int j = 0; j < n; j++) {
                sum[i + 1][j + 1] = sum[i][j + 1] + sum[i + 1][j] - sum[i][j] + matrix[i][j];
            }
        }
    }

    public void update(int row, int col, int val) {
        updates.put(row * n + col, val);
        if (updates.size() > updateLimit) {
            for (Map.Entry<Integer, Integer> update: updates.entrySet()) {
                int key = update.getKey();
                matrix[key / n][key % n] = update.getValue();
            }
            calculateSum();
            updates.clear();
        }
    }

    public int sumRegion(int row1, int col1, int row2, int col2) {
        int result = sum[row2 + 1][col2 + 1] - sum[row1][col2 + 1] - sum[row2 + 1][col1] + sum[row1][col1];
        for (Map.Entry<Integer, Integer> update: updates.entrySet()) {
            int key = update.getKey();
            int r = key / n, c = key % n;
            if (r >= row1 && r <= row2 && c >= col1 && c <= col2) {
                result += (update.getValue() - matrix[r][c]);
            }
        }
        return result;
    }
}
