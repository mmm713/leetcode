package com.home.learn.leetcode;

import java.util.Stack;

public class LargestRectangleInHistogram {
    public int largestRectangleArea(int[] heights) {
        Stack<Integer> stack = new Stack<>();
        int result = 0;
        for (int i = 0; i < heights.length; ++i) {
            while (!stack.isEmpty() && heights[stack.peek()] >= heights[i]) {
                int idx = stack.pop();
                int origin = stack.isEmpty() ? -1 : stack.peek();
                result = Math.max(result, heights[idx] * (i - origin - 1));
            }
            stack.push(i);
        }
        while (!stack.isEmpty()) {
            int idx = stack.pop();
            int origin = stack.isEmpty() ? -1 : stack.peek();
            result = Math.max(result, heights[idx] * (heights.length - origin - 1));
        }
        return result;
    }
}
