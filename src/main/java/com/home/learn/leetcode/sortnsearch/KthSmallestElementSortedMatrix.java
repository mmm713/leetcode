package com.home.learn.leetcode.sortnsearch;

public class KthSmallestElementSortedMatrix {
    public int kthSmallest(int[][] matrix, int k) {
        int left = matrix[0][0], right = matrix[matrix.length - 1][matrix[0].length - 1];
        while(left < right) {
            int mid = left + (right - left) / 2;
            int cnt = searchLessEqual(matrix, mid);
            if(cnt < k) left = mid + 1;
            else right = mid;
        }
        return left;
    }
    int searchLessEqual(int[][] matrix, int k) {
        int i = 0, j = matrix[0].length - 1, cnt = 0;
        while(i < matrix.length && j >= 0) {
            if(matrix[i][j] <= k) {
                cnt += ++j;
                i++;
            }
            j--;
        }
        return cnt;
    }
}
