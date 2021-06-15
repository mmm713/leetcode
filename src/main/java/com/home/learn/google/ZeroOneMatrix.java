package com.home.learn.google;

public class ZeroOneMatrix {
    private static final int[][] directions = new int[][] {{1,0},{-1,0},{0,1},{0,-1}};

    public int[][] updateMatrix(int[][] matrix) {
        if (matrix.length == 0) return matrix;
        int[][] res = new int[matrix.length][matrix[0].length];
        boolean[][] visited = new boolean[matrix.length][matrix[0].length];
        boolean[][] determined = new boolean[matrix.length][matrix[0].length];
        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                udpateHelper(matrix, res, visited, determined, i, j);
            }
        }
        return res;
    }

    private int udpateHelper(int[][] matrix, int[][] res, boolean[][] visited, boolean[][] determined, int x, int y) {
        if(matrix[x][y] == 0) {
            return 0;
        }
        int min = Integer.MAX_VALUE - 10;
        visited[x][y] = true;
        for (int[] dir : directions) {
            int i = x + dir[0], j = y + dir[1];
            if (i >= 0 && i < res.length && j >= 0 && j < res[0].length) {
                if (matrix[i][j] == 0) {
                    min = 0;
                    break;
                } else if(determined[i][j]) {
                    min = Math.min(min, res[i][j]);
                } else if(!visited[i][j]) {
                    min = Math.min(min, udpateHelper(matrix, res, visited, determined, i, j));
                }
            }
        }
        res[x][y] = ++min;
        determined[x][y] = true;
        return min;
    }
}
