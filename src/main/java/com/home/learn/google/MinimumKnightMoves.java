package com.home.learn.google;

public class MinimumKnightMoves {
    private static final int[][] dirs = new int[][] {{-1,-2},{-2,-1},{-2,1},{-1,2}};
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int t = x;
            x = y;
            y = t;
        }
        int yLimit = Math.max(y + 3, 5);
        int[][] steps = new int[Math.max(x + 1, 3)][yLimit];
        steps[0][0] = 0;
        steps[0][1] = 3;
        steps[0][2] = 2;
        steps[1][0] = 3;
        steps[1][1] = 2;
        steps[1][2] = 1;
        steps[2][0] = 2;
        steps[2][1] = 1;
        steps[2][2] = 4;
        for(int i = 3; i <= x; i++) {
            for(int j = 0; j <= Math.min(i, yLimit - 1); j++) {
                for (int[] dir : dirs) {
                    int nx = i + dir[0];
                    int ny = j + dir[1];
                    if(nx < 0|| ny < 0 || ny > y +2 || steps[nx][ny] == 0) {
                        continue;
                    }
                    if(steps[i][j] == 0) {
                        steps[i][j] = steps[nx][ny] + 1;
                    } else {
                        steps[i][j] = Math.min(steps[nx][ny] + 1, steps[i][j]);
                    }
                }
                if(i == x && j == y) {
                    break;
                }
            }
        }

        return steps[x][y];
    }
}
