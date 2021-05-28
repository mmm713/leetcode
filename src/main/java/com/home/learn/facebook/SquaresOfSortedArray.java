package com.home.learn.facebook;

public class SquaresOfSortedArray {
    public int[] sortedSquares(int[] A) {
        int n = A.length;
        int j = 0;
        while(j < n && A[j] < 0) {
            ++j;
        }
        int i = j - 1;
        int t = 0;
        int[] squares = new int[n];
        while(i >= 0 && j < n){
            if(A[i] * A[i] < A[j] * A[j]){
                squares[t++] = A[i] * A[i];
                --i;
            } else {
                squares[t++] = A[j] * A[j];
                ++j;
            }
        }
        while(i >= 0){
            squares[t++] = A[i] * A[i];
            --i;
        }
        while(j < n){
            squares[t++] = A[j] * A[j];
            ++j;
        }
        return squares;
    }
}
