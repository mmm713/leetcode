package com.home.learn.facebook;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;
import java.util.Stack;

public class SwimRisingWater {
    //dp解法，很坏的选择
    private static final int[][] dirs = {{0, 1}, {1, 0}, {0, -1}, {-1, 0}};
    public int swimInWater(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        for (int[] p : dp) {
            Arrays.fill(p, Integer.MAX_VALUE);
        }
        helper(grid, 0, 0, grid[0][0], dp, n);
        return dp[n - 1][n - 1];
    }
    public void helper(int[][] grid, int x, int y, int cur, int[][] dp, int n) {
        if (x < 0 || x >= n || y < 0 || y >= n || Math.max(cur, grid[x][y]) >= dp[x][y]) return;
        dp[x][y] = Math.max(cur, grid[x][y]);
        for (int[] dir : dirs) {
            helper(grid, x + dir[0], y + dir[1], dp[x][y], dp, n);
        }
    }

    //Time Complexity: O(N^2 \log N) Our depth-first search during a call to
    // possible is O(N^2), and we make up to O(\log N)O(logN) of them.
    //
    //Space Complexity: O(N^2) the maximum size of the stack.
    public int swimInWaterBinarySearch(int[][] grid) {
        int N = grid.length;
        int lo = grid[0][0], hi = N * N;
        while (lo < hi) {
            int mi = lo + (hi - lo) / 2;
            if (!possible(mi, grid)) {
                lo = mi + 1;
            } else {
                hi = mi;
            }
        }
        return lo;
    }

    public boolean possible(int T, int[][] grid) {
        int N = grid.length;
        Set<Integer> seen = new HashSet<>();
        seen.add(0);
        int[] dr = new int[]{1, -1, 0, 0};
        int[] dc = new int[]{0, 0, 1, -1};

        Stack<Integer> stack = new Stack<>();
        stack.add(0);

        while (!stack.empty()) {
            int k = stack.pop();
            int r = k / N, c = k % N;
            if (r == N-1 && c == N-1) return true;

            for (int i = 0; i < 4; ++i) {
                int cr = r + dr[i], cc = c + dc[i];
                int ck = cr * N + cc;
                if (0 <= cr && cr < N && 0 <= cc && cc < N
                        && !seen.contains(ck) && grid[cr][cc] <= T) {
                    stack.add(ck);
                    seen.add(ck);
                }
            }
        }

        return false;
    }
}
