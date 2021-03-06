package com.home.learn.pinterest;

import java.util.Arrays;

public class GraphValidTree {
    public boolean validTree(int n, int[][] edges) {
        // initialize n isolated islands
        int[] nums = new int[n];
        Arrays.fill(nums, -1);
        for (int[] edge : edges) {
            int x = find(nums, edge[0]);
            int y = find(nums, edge[1]);
            if (x == y) return false;
            nums[x] = y;
        }
        return edges.length == n - 1;
    }

    int find(int[] nums, int i) {
        if (nums[i] == -1) return i;
        return find(nums, nums[i]);
    }
}
