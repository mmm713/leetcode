package com.home.learn.facebook;

import java.util.ArrayList;
import java.util.List;

public class SpiralMatrix {
    private enum state {up, down, left, right}
    //返回顺时针螺旋向内结果
    public List<Integer> spiralOrder(int[][] matrix) {
        List<Integer> result = new ArrayList<>();
        int m = matrix.length;
        int n = matrix[0].length;
        state state = SpiralMatrix.state.right;
        int boundaryRight = n - 1, boundaryLeft = 0, boundaryUp = 1, boundaryDown = m - 1;
        int x = 0, y = 0; // cursor, x - row, y - col
        for(int i = 0; i < m * n; i++) {
            result.add(matrix[x][y]);
            switch(state) {
                case up:
                    if(x > boundaryUp) {
                        x--;
                    } else {
                        boundaryUp++;
                        y++;
                        state = SpiralMatrix.state.right;
                    }
                    break;
                case down:
                    if(x < boundaryDown) {
                        x++;
                    } else {
                        boundaryDown--;
                        y--;
                        state = SpiralMatrix.state.left;
                    }
                    break;
                case left:
                    if(y > boundaryLeft) {
                        y--;
                    } else {
                        boundaryLeft++;
                        x--;
                        state = SpiralMatrix.state.up;
                    }
                    break;
                case right:
                    if(y < boundaryRight) {
                        y++;
                    } else {
                        boundaryRight--;
                        x++;
                        state = SpiralMatrix.state.down;
                    }
                    break;
            }
        }
        return result;
    }

    //顺时针螺旋向内写
    public int[][] generateMatrix(int n) {
        int[][] result = new int[n][n];
        state state = SpiralMatrix.state.right;
        int boundaryRight = n - 1, boundaryLeft = 0, boundaryUp = 1, boundaryDown = n - 1;
        int x = 0, y = 0; // cursor, x - row, y - col
        for(int i = 1; i<= n * n; i++) {
            result[x][y] = i;
            switch(state) {
                case up:
                    if(x > boundaryUp) {
                        x--;
                    } else {
                        boundaryUp++;
                        y++;
                        state = SpiralMatrix.state.right;
                    }
                    break;
                case down:
                    if(x < boundaryDown) {
                        x++;
                    } else {
                        boundaryDown--;
                        y--;
                        state = SpiralMatrix.state.left;
                    }
                    break;
                case left:
                    if(y > boundaryLeft) {
                        y--;
                    } else {
                        boundaryLeft++;
                        x--;
                        state = SpiralMatrix.state.up;
                    }
                    break;
                case right:
                    if(y < boundaryRight) {
                        y++;
                    } else {
                        boundaryRight--;
                        x++;
                        state = SpiralMatrix.state.down;
                    }
                    break;
            }
        }
        return result;
    }

    //从任意点起顺时针螺旋访问
    public int[][] spiralMatrixIII(int rows, int cols, int rStart, int cStart) {
        int[][] result = new int[rows * cols][2];
        result[rows * cols - 1][0] = -1;
        state state = SpiralMatrix.state.right;
        int boundaryRight = cStart + 1, boundaryLeft = cStart - 1, boundaryUp = rStart - 1, boundaryDown = rStart + 1;
        int x = rStart, y = cStart; // cursor, x - row, y - col
        int i = 0;
        while(result[rows * cols - 1][0] == -1) {
            if(x >=0 && x < rows && y >= 0 && y < cols) {
                result[i][0] = x;
                result[i][1] = y;
                i++;
            }
            switch(state) {
                case up:
                    if(x > boundaryUp && x >= 0) {
                        x--;
                    } else {
                        boundaryUp--;
                        y++;
                        state = SpiralMatrix.state.right;
                    }
                    break;
                case down:
                    if(x < boundaryDown && x < rows) {
                        x++;
                    } else {
                        boundaryDown++;
                        y--;
                        state = SpiralMatrix.state.left;
                    }
                    break;
                case left:
                    if(y > boundaryLeft && y >= 0) {
                        y--;
                    } else {
                        boundaryLeft--;
                        x--;
                        state = SpiralMatrix.state.up;
                    }
                    break;
                case right:
                    if(y < boundaryRight && y < cols) {
                        y++;
                    } else {
                        boundaryRight++;
                        x++;
                        state = SpiralMatrix.state.down;
                    }
                    break;
            }
        }
        return result;
    }
}
