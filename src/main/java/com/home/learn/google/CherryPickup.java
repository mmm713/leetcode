package com.home.learn.google;

import java.util.Arrays;

public class CherryPickup {
    // 我们的dp[i][j]还是需要定义为round trip的，即到达(i, j)位置并返回起点时能捡到的最大樱桃数，
    // 但是新的问题就来了，樱桃只有一个，只能捡一次，去程捡了，返程就不能再捡了，如何才能避免重复计算呢？
    // 我们只有i和j是不够的，其只能定义去程的位置，我们还需要pg，来定义返程的位置，
    // 那么重现关系Recurrence Relations就变成了 T(i, j, p, g)，
    // 我们有分别两种方式离开(i, j)和(p, g)，我们suppose时从终点往起点遍历，那么就有4种情况：
    //
    //Case 1: (0, 0) ==> (i-1, j) ==> (i, j); (p, q) ==> (p-1, q) ==> (0, 0)
    //Case 2: (0, 0) ==> (i-1, j) ==> (i, j); (p, q) ==> (p, q-1) ==> (0, 0)
    //Case 3: (0, 0) ==> (i, j-1) ==> (i, j); (p, q) ==> (p-1, q) ==> (0, 0)
    //Case 4: (0, 0) ==> (i, j-1) ==> (i, j); (p, q) ==> (p, q-1) ==> (0, 0)
    //根据定义，我们有：
    //
    //Case 1 is equivalent to T(i-1, j, p-1, q) + grid[i][j] + grid[p][q];
    //Case 2 is equivalent to T(i-1, j, p, q-1) + grid[i][j] + grid[p][q];
    //Case 3 is equivalent to T(i, j-1, p-1, q) + grid[i][j] + grid[p][q];
    //Case 4 is equivalent to T(i, j-1, p, q-1) + grid[i][j] + grid[p][q];
    //因此，我们的重现关系可以写作：
    //
    // T(i, j, p, q) = grid[i][j] + grid[p][q] + max{T(i-1, j, p-1, q), T(i-1, j, p, q-1), T(i, j-1, p-1, q), T(i, j-1, p, q-1)}
    // 为了避免重复计算，我们希望 grid[i][j] 和 grid[p][g] 不出现在T(i-1, j, p-1, q), T(i-1, j, p, q-1), T(i, j-1, p-1, q) 和 T(i, j-1, p, q-1)中的任意一个上。
    // 显而易见的是(i, j)不会出现在(0, 0) ==> (i-1, j) 或 (0, 0) ==> (i, j-1) 的路径上，同理，(p, g) 也不会出现在 (p-1, q) ==> (0, 0) 或 (p, q-1) ==> (0, 0) 的路径上。
    // 因此，我们需要保证(i, j) 不会出现在 (p-1, q) ==> (0, 0) 或 (p, q-1) ==> (0, 0) 的路径上，
    // 同时 (p, g)不会出现在(0, 0) ==> (i-1, j) 或 (0, 0) ==> (i, j-1) 的路径上，怎么做呢？
    //
    // 我们观察到(0, 0) ==> (i-1, j) 和 (0, 0) ==> (i, j-1) 的所有点都在矩形 [0, 0, i, j] 中（除了右下角点(i, j)点），
    // 所以只要 (p, g) 不在矩形 [0, 0, i, j] 中就行了，注意(p, g) 和 (i, j) 是有可能重合了，这种情况特殊处理一下就行了。
    // 同理， (i, j) 也不能在矩形 [0, 0, p, g] 中，那么以下三个条件中需要满足一个：
    //
    // i < p && j > q
    // i == p && j == q
    // i > p && j < q
    // 为了满足上述条件，我们希望当 i 或 p 增加的时候，j 或 q 减小，那么我们可以有这个等式:
    //
    // k = i + j = p + q
    // 其中k为从起点开始走的步数，所以我们可以用 T(k, i, p)  来代替 T(i, j, p, g)，那么我们的重现关系式就变成了：
    //
    // T(k, i, p) = grid[i][k-i] + grid[p][k-p] + max{T(k-1, i-1, p-1), T(k-1, i-1, p), T(k-1, i, p-1), T(k-1, i, p)}.
    // 当 i == p 时，grid[i][k-i] 和 grid[p][k-p] 就相等了，此时只能加一个。我们注意到 i, j, p, q 的范围是 [0, n)，
    // 意味着k只能在范围 [0, 2n - 1) 中， 初始化时 T(0, 0, 0) = grid[0][0]。
    // 我们这里的重现关系T虽然是三维的，但是我们可以用二维dp数组来实现，因为第k步的值只依赖于第k-1步的情况

    public int cherryPickup(int[][] grid) {
        int n = grid.length;
        int[][] dp = new int[n][n];
        for(int[] p : dp) Arrays.fill(p, Integer.MIN_VALUE);
        dp[0][0] = grid[0][0];
        for (int k = 1; k < 2 * n - 1; ++k) {
            if(k == 2 * n - 2) {
                update(dp, grid, n, k, n - 1, n - 1);
            } else {
                for (int i = n - 1; i >= 0; --i) {
                    for (int p = n - 1; p >= 0; --p) {
                        update(dp, grid, n, k, i, p);
                    }
                }
            }
        }
        return Math.max(dp[n - 1][n - 1], 0);
    }

    private void update(int[][] dp, int[][] grid, int n, int k, int i, int p) {
        int j = k - i;
        int q = k - p;
        if (j < 0 || j >= n || q < 0 || q >= n || grid[i][j] < 0 || grid[p][q] < 0) {
            dp[i][p] = Integer.MIN_VALUE;
            return;
        }
        if (i > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p]);
        if (p > 0) dp[i][p] = Math.max(dp[i][p], dp[i][p - 1]);
        if (i > 0 && p > 0) dp[i][p] = Math.max(dp[i][p], dp[i - 1][p - 1]);
        if (dp[i][p] >= 0) dp[i][p] += grid[i][j] + (i != p ? grid[p][q] : 0);
    }
}
