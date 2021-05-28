package com.home.learn.microsoft;

public class DungeonGame {
    public int calculateMinimumHP(int[][] dungeon) {
        int m = dungeon.length;
        int n = dungeon[0].length;
        for (int i = m - 1; i >= 0 ; i--) {
            for (int j = n - 1; j >= 0 ; j--) {
                int tmp = i == m - 1 && j == n - 1 ? 1 : Integer.MAX_VALUE;
                if(i + 1 < m) {
                    tmp = Math.min(tmp, dungeon[i + 1][j]);
                }
                if(j + 1 < n) {
                    tmp = Math.min(tmp, dungeon[i][j + 1]);
                }
                dungeon[i][j] = tmp - dungeon[i][j] <= 0 ? 1 : tmp - dungeon[i][j];
            }
        }
        return dungeon[0][0];
    }
}
