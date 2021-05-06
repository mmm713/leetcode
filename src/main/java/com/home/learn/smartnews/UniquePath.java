package com.home.learn.smartnews;

public class UniquePath {
    public int uniquePaths(int m, int n) {
        return (int)combination(m+n-2, Math.max(m-1, n-1));
    }
    private long factor(int n, int start) {
        long ret = 1;
        for(int i = start; i <= n; ++i)
            ret *= i;
        return ret;
    }
    private long combination(int n, int k)  {
        if (k == 0) return 1;
        if (k == 1) return n;
        long ret = factor(n, k+1);
        ret /= factor(n - k, 1);
        return ret;
    }

    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        if(obstacleGrid[0][0] == 1) {
            return 0;
        }
        obstacleGrid[0][0] = 1;
        for(int i = 1; i < m; i++) {
            if(obstacleGrid[i][0] == 1) {
                obstacleGrid[i][0] = 0;
            } else {
                obstacleGrid[i][0] = obstacleGrid[i - 1][0];
            }
        }
        for(int j = 1; j < n; j++) {
            if(obstacleGrid[0][j] == 1)
            {
                obstacleGrid[0][j] = 0;
            } else {
                obstacleGrid[0][j] = obstacleGrid[0][j - 1];
            }
        }
        for(int i = 1; i < m; i++) {
            for (int j = 1; j < n; j++)
            {
                if(obstacleGrid[i][j] == 1){
                    obstacleGrid[i][j] = 0;
                } else {
                    obstacleGrid[i][j] = obstacleGrid[i][j - 1] + obstacleGrid[i - 1][j];
                }
            }
        }
        return obstacleGrid[m - 1][n - 1];
    }
}
