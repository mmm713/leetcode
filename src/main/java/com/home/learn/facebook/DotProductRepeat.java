package com.home.learn.facebook;

import java.util.Stack;

public class DotProductRepeat {
    public int dotProduct(int[] A, int[] B) {
        Stack<int[]> a = count(A);
        Stack<int[]> b = count(B);
        int ans = 0;
        while (!a.isEmpty() && !b.isEmpty()) {
            int[] x = a.pop();
            int[] y = b.pop();
            int times = Math.min(x[1], y[1]);
            ans += x[0] * y[0] * times;
            x[1] -= times;
            y[1] -= times;
            if (x[1] != 0) {
                a.add(x);
            }
            if (y[1] != 0) {
                b.add(y);
            }
        }
        return ans;
    }

    public Stack<int[]> count(int[] nums) {
        int l = 0, r = 0;
        Stack<int[]> stack = new Stack<>();
        while (r < nums.length) {
            if (nums[r] != nums[l]) {
                stack.add(new int[] { nums[l], r - l });
                l = r;
            }
            r++;
        }

        stack.add(new int[] { nums[l], r - l });
        return stack;
    }
}
