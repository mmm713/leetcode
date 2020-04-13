package com.home.learn.leetcode;


import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class SmallestRangeCoverKLists {
    public int[] smallestRange(List<List<Integer>> nums) {
        PriorityQueue<int[]> minHeap = new PriorityQueue<>(nums.size(), Comparator.comparingInt(o -> o[0]));
        int max = nums.get(0).get(0);
        for (int i = 0; i < nums.size(); i++) {
            minHeap.add(new int[] {nums.get(i).get(0), i, 0});
            max = Math.max(max, nums.get(i).get(0));
        }
        int range = Integer.MAX_VALUE;
        int start = -1, end = -1;
        while (minHeap.size() == nums.size()) {
            int[] t = minHeap.poll();
            if (max - t[0] < range) {
                range = max - t[0];
                start = t[0];
                end = max;
            }
            if (t[2] < nums.get(t[1]).size() - 1) {
                t[0] = nums.get(t[1]).get(++t[2]);
                minHeap.add(t);
                max = Math.max(max, t[0]);
            }
        }
        return new int[] {start, end};
    }
}
