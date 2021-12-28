package com.home.learn.amazon;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Stack;

public class SumSubarrayMinimums {

    public int sumSubarrayMins(int[] arr) {
        int ans = 0, len = arr.length + 1;
        int[] extended = new int[len], prevLess = new int[len], sums = new int[len];
        prevLess[0] = Integer.MIN_VALUE;
        System.arraycopy(arr, 0, extended, 1, len - 1); // extended = {0, 3, 1, 2, 4};

        for (int i = 1; i < len; i++) { // consider original num only
            int num = extended[i], ls = i - 1; // ls = 0 means no previous smaller
            while(extended[ls] >= num) ls = prevLess[ls]; // extended[0] >= num always fails
            prevLess[i] = ls;

            sums[i] = num * (i - ls) + sums[ls]; // if ls = 0, then sums[ls] = 0
            ans = (ans + sums[i]) % 1_000_000_007;
        }
        return ans;
    }

    public int sumSubarrayMinsV1(int[] arr) {
        int MOD = 1_000_000_007;
        int ans = 0;
        Stack<Integer> lStack = new Stack<>();
        Stack<Integer> rStack = new Stack<>();
        int[] left = new int[arr.length];
        int[] right = new int[arr.length];

        for (int i = 0; i < arr.length; i++) {
            if(lStack.isEmpty() || arr[i] > arr[lStack.peek()]) {
                left[i] = i;
            } else {
                while (!lStack.isEmpty() && arr[i] <= arr[lStack.peek()]) {
                    lStack.pop();
                }
                if(lStack.isEmpty()) {
                    left[i] = 0;
                } else {
                    left[i] = lStack.peek();
                }
            }
            lStack.push(i);
            int ii = arr.length - 1 - i;
            if(rStack.isEmpty() || arr[ii] > arr[rStack.peek()]) {
                right[ii] = ii;
            } else {
                while (!rStack.isEmpty() && arr[ii] < arr[rStack.peek()]) {
                    rStack.pop();
                }
                if(rStack.isEmpty()) {
                    right[ii] = arr.length - 1;
                } else {
                    right[ii] = rStack.peek() - 1;
                }
            }
            rStack.push(ii);
        }
        for (int i = 0; i < arr.length; i++) {
            ans += (long) (i - left[i] + 1) * (right[i] - i + 1) % MOD * arr[i] % MOD;
            ans %= MOD;
        }
        return ans;
    }
    public int sumSubarrayMinsV2(int[] A) {
        int MOD = 1_000_000_007;
        int N = A.length;

        // 第 1 步：计算当前下标 i 的左边第 1 个比 A[i] 小的元素的下标
        Deque<Integer> stack1 = new ArrayDeque<>();
        int[] prev = new int[N];
        for (int i = 0; i < N; i++) {
            while (!stack1.isEmpty() && A[i] <= A[stack1.peekLast()]) {
                stack1.removeLast();
            }
            prev[i] = stack1.isEmpty() ? -1 : stack1.peekLast();
            stack1.addLast(i);
        }

        // 第 2 步：计算当前下标 i 的右边第 1 个比 A[i] 小的元素的下标
        Deque<Integer> stack2 = new ArrayDeque<>();
        int[] next = new int[N];
        for (int i = N - 1; i >= 0; i--) {
            while (!stack2.isEmpty() && A[i] < A[stack2.peekLast()]) {
                stack2.removeLast();
            }
            next[i] = stack2.isEmpty() ? N : stack2.peekLast();
            stack2.addLast(i);
        }

        // 第 3 步：计算结果
        long ans = 0;
        for (int i = 0; i < N; ++i) {
            // 注意：乘法可能越界，须要先转成 long 类型
            ans += (long) (i - prev[i]) * (next[i] - i) % MOD * A[i] % MOD;
            ans %= MOD;
        }
        return (int) ans;
    }
}
