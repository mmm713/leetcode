package com.home.learn.pinterest;

import com.home.learn.leetcode.LargestRectangleInHistogram;


public class MaximalRectangle {

    public int maximalRectangle(char[][] matrix) {
        if (matrix.length == 0) return 0;
        int result = 0;
        int[] dp = new int[matrix[0].length];
        LargestRectangleInHistogram histogram = new LargestRectangleInHistogram();
        for (char[] chars : matrix) {
            for (int j = 0; j < matrix[0].length; j++) {
                // update the state of this row's histogram using the last row's histogram
                // by keeping track of the number of consecutive ones
                dp[j] = chars[j] == '1' ? dp[j] + 1 : 0;
            }
            // update result with the maximum area from this row's histogram
            result = Math.max(result, histogram.largestRectangleArea(dp));
        }
        return result;
    }
}
