package com.home.learn.google;

import java.util.Arrays;

public class MaximumPathFromEntryToExit {
    public int maxPath(int[][] matrix) {
        int[][][] dp = new int[matrix.length + 2][matrix[0].length + 2][3];
        for(int[][] d : dp) {
            for(int[] p : d) {
                Arrays.fill(p, -1);
            }
        }
        for(int j = 0; j < matrix[0].length + 2; j++) {
            dp[0][j][0] = 0;
            dp[0][j][1] = 0;
            dp[0][j][2] = 0;
        }
        for (int i = 0; i < matrix.length + 2; i++) {
            dp[i][0][0] = 0;
            dp[i][0][1] = 0;
            dp[i][0][2] = 0;
            dp[i][matrix[0].length + 1][0] = 0;
            dp[i][matrix[0].length + 1][1] = 0;
            dp[i][matrix[0].length + 1][2] = 0;
        }
        int res = 0;
        for (int i = 1; i < matrix.length + 1; i++) {
            for (int j = 1; j < matrix[0].length + 1; j++) {
                for (int k = 0; k < 3; k++) {
                    res = Math.max(res, find(matrix, dp, i, j, k));
                }
            }
        }
        return res;
    }

    private int find(int[][] matrix, int[][][] dp, int i, int j, int k) {
        if(dp[i][j][k] != -1) {
            return dp[i][j][k];
        }
        //当前位置不可走
        if(matrix[i - 1][j - 1] == 0) {
            return 0;
        }
        dp[i][j][k] = 0;
        if(k == 0) {
            dp[i][j][k] = Math.max(find(matrix, dp, i, j + 1, 0),
                    find(matrix, dp, i, j + 1, 2)) + 1;
        } else if(k == 1) {
            dp[i][j][k] = Math.max(find(matrix, dp, i, j - 1, 1),
                    find(matrix, dp, i, j - 1, 2)) + 1;
        } else {
            dp[i][j][k] = Math.max(find(matrix, dp, i - 1, j, 2),
                    Math.max(find(matrix, dp, i - 1, j, 0),
                            find(matrix, dp, i - 1, j, 1))) + 1;
        }
        return dp[i][j][k];
    }
}
