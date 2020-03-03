package com.home.learn.leetcode.subarray;

import java.util.ArrayDeque;
import java.util.Deque;

public class ShortestSubarraySumAtLeastK {
    public int shortestSubarray(int[] A, int K) {
        int[] P = new int[A.length + 1];
        for (int i = 0; i < P.length - 1; ++i) {
            P[i + 1] = P[i] + A[i];
        }
        int ans = Integer.MAX_VALUE;
        Deque<Integer> deque = new ArrayDeque<>(A.length);
        for (int i = 0; i < P.length; ++i) {
            while (!deque.isEmpty() && P[i] <= P[deque.peekLast()]) {
                deque.removeLast();
            }
            while (!deque.isEmpty() && P[i] - P[deque.peekFirst()] >= K) {
                ans = Math.min(ans, i - deque.pollFirst());
            }
            deque.addLast(i);
        }
        return ans < P.length ? ans : -1;
    }
}
