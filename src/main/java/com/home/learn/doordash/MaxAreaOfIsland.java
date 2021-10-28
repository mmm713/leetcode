package com.home.learn.doordash;

public class MaxAreaOfIsland {
    private static int[][] dir = { { 1,0 },{ -1,0 }, { 0,1 },{ 0,-1 } };
    public int maxAreaOfIsland(int[][] grid) {
        int result = 0;
        if(grid.length < 1) return result;
        int m = grid.length, n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[0].length; j++) {
                if ((!visited[i][j]) && (grid[i][j] == 1)) {
                    result = Math.max(result, dfsFind(grid, visited, i, j));
                }
            }
        }
        return result;

    }
    public int dfsFind(int[][] grid, boolean[][] visited, int i, int j) {
        int area = 1;
        visited[i][j] = true;
        for (int k = 0; k < 4; k++) {
            int x = i + dir[k][0];
            int y = j + dir[k][1];
            if (x >= 0 && x < grid.length && y >= 0 && y < grid[0].length && grid[x][y] == 1 && !visited[x][y]) {
                area += dfsFind(grid, visited, x, y);
            }
        }
        return area;
    }
}
