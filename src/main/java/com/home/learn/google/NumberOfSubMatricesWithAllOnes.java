package com.home.learn.google;


import org.apache.commons.lang3.tuple.Pair;

import java.util.Stack;

public class NumberOfSubMatricesWithAllOnes {
    //https://www.geeksforgeeks.org/number-of-submatrices-with-all-1s/
    // Function to count the number of sub-matrices with all 1s
    public int matrixAllOne(int[][] arr) {
        int m = arr.length;
        int n = arr[0].length;
        // Array to store required prefix count of
        // 1s from right to left for boolean array
        int [][]p_arr = new int[n][n];
        for (int i = 0; i < m; i++) {
            for (int j = n - 1; j >= 0; j--) {
                if (arr[i][j] == 0)
                    continue;
                p_arr[i][j] = 1;
                if (j != n - 1)
                    p_arr[i][j] += p_arr[i][j + 1];
            }
        }
        // variable to store the final answer
        int ans = 0;
        /* Loop to evaluate each column of
            the prefix matrix uniquely.
            For each index of a column we will try to
            determine the number of sub-matrices
            starting from that index
            and has all 1s */
        for (int j = 0; j < n; j++) {
            Stack<Pair<Integer, Integer>> stack = new Stack<>();
            int tempSum = 0;
            for(int i = m - 1; i >= 0; i--) {
                int count = 0;
                while (!stack.isEmpty() && stack.peek().getKey() > p_arr[i][j]) {
                    tempSum -= (stack.peek().getValue() + 1) * (stack.peek().getKey() - p_arr[i][j]);
                    count += stack.peek().getValue() + 1;
                    stack.pop();
                }
                tempSum += p_arr[i][j];
                ans += tempSum;
                stack.add(Pair.of(p_arr[i][j], count));
            }
        }
        return ans;
    }
}
