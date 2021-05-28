package com.home.learn.bytedance;

public class Search2DMatrix {
    public boolean searchMatrixII(int[][] matrix, int target) {
        int col = 0; //col
        int row = matrix.length - 1; //row
        while(row >= 0 && col <= matrix[0].length - 1) {
            if(target > matrix[row][col]){
                col++;
            } else if (target < matrix[row][col]){
                row--;
            } else if (target == matrix[row][col]){
                return true;
            }
        }
        return false;
    }


    public boolean searchMatrix(int[][] matrix, int target) {
        int row = 0;
        if(target < matrix[0][0] || target > matrix[matrix.length - 1][matrix[0].length - 1]) {
            return false;
        }
        while(row < matrix.length) {
            if(matrix[row][0] < target) {
                row++;
            } else if(matrix[row][0] > target) {
                break;
            } else {
                return true;
            }
        }
        row--;
        int col = matrix[0].length - 1;
        while(col > 0) {
            if(matrix[row][col] < target) {
                return false;
            } else if(matrix[row][col] > target) {
                col--;
            } else {
                return true;
            }
        }
        return false;
    }
}
