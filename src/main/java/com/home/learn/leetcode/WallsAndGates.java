package com.home.learn.leetcode;

public class WallsAndGates {
    private static final int[][] directions = {{1, 0}, {-1, 0}, {0, 1}, {0, -1}};

    public void wallsAndGates(int[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                if(rooms[i][j] == 0) {
                    dfs(rooms, i, j, 0);
                }
            }
        }
    }

    private void dfs(int[][] rooms, int i, int j, int val) {
        if(rooms[i][j] < val) return;
        rooms[i][j] = val;
        for (int[] direction : directions) {
            int x = i + direction[0];
            int y = j + direction[1];
            if (x >= 0 && x < rooms.length && y >= 0 && y < rooms[x].length) {
                dfs(rooms, x, y, val + 1);
            }
        }
    }
}
