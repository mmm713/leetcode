package com.home.learn.facebook;

public class MatrixBlockSum {
    public int[][] matrixBlockSum(int[][] mat, int k) {
        int m = mat.length, n = mat[0].length;
        //get pre sum
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                if(i > 0) mat[i][j] += mat[i - 1][j];
                if(j > 0) mat[i][j] += mat[i][j - 1];
                if(i > 0 && j > 0)  mat[i][j] -= mat[i - 1][j - 1];
            }
        }
        int[][] res = new int[m][n];
        for(int i = 0; i < m; i++) {
            for(int j = 0; j < n; j++) {
                int x = Math.min(i + k, m - 1);
                int y = Math.min(j + k, n - 1);
                //查看顶端是否封顶
                int xx = Math.max(i - k, 0);
                int yy = Math.max(j - k, 0);

                res[i][j] = mat[x][y];
                if(xx > 0) res[i][j] -= mat[xx - 1][y];
                if(yy > 0) res[i][j] -= mat[x][yy - 1];
                if(xx > 0 && yy > 0) res[i][j] += mat[xx - 1][yy - 1];

            }
        }
        return res;
    }
}
