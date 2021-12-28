package com.home.learn.google;

public class MinimumKnightMoves {
    //因为对称关系，所有的xy都可以在0到45度角内的第一象限找到映射。且3X3范围内的步数是确定的（自己试下就有了）。
    // 那么3X3以外的点你可以往回找。坐标3,2可以查看坐标1,1 坐标2,0，坐标2,4，坐标1,3的结果，
    // 取最小加一就是坐标3,2的步数，以此类推直到终点。另外不要忘记只需要看0-45度角内的，其实大于Y+2的也不用看。以此可以省略查询。
    private static final int[][] dirs = new int[][] {{-1,-2},{-2,-1},{-2,1},{-1,2}};
    public int minKnightMoves(int x, int y) {
        x = Math.abs(x);
        y = Math.abs(y);
        if (x < y) {
            int t = x;
            x = y;
            y = t;
        }
        //考虑到X方向和Y方向最多可以走过头1-3个单位
        int xLimit = Math.max(x + 1, 3);
        int yLimit = Math.max(y + 3, 5);
        int[][] steps = new int[xLimit][yLimit];
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
