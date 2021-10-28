package com.home.learn.microsoft;

public class StoneGame {

    //定义二维数组 \textit{dp}dp，其行数和列数都等于石子的堆数，\textit{dp}[i][j]dp[i][j] 表示当剩下的石子堆为下标 ii 到下标 jj 时，即在下标范围 [i, j][i,j] 中，当前玩家与另一个玩家的石子数量之差的最大值，注意当前玩家不一定是先手 \text{Alice}Alice。
    //
    //只有当 i \le ji≤j 时，剩下的石子堆才有意义，因此当 i>ji>j 时，\textit{dp}[i][j]=0dp[i][j]=0。
    //
    //当 i=ji=j 时，只剩下一堆石子，当前玩家只能取走这堆石子，因此对于所有 0 \le i < \textit{nums}.\text{length}0≤i<nums.length，都有 \textit{dp}[i][i]=\textit{piles}[i]dp[i][i]=piles[i]。
    //
    //当 i<ji<j 时，当前玩家可以选择取走 \textit{piles}[i]piles[i] 或 \textit{piles}[j]piles[j]，然后轮到另一个玩家在剩下的石子堆中取走石子。在两种方案中，当前玩家会选择最优的方案，使得自己的石子数量最大化。因此可以得到如下状态转移方程：
    //
    //\textit{dp}[i][j]=\max(\textit{piles}[i] - \textit{dp}[i+1][j], \textit{piles}[j] - \textit{dp}[i][j-1])
    //dp[i][j]=max(piles[i]−dp[i+1][j],piles[j]−dp[i][j−1])
    //
    //最后判断 \textit{dp}[0][\textit{piles}.\text{length}-1]dp[0][piles.length−1] 的值，如果大于 00，则 \text{Alice}Alice 的石子数量大于 \text{Bob}Bob 的石子数量，因此 \text{Alice}Alice 赢得比赛，否则 \text{Bob}Bob 赢得比赛。
    //
    public boolean stoneGame(int[] piles) {
        int length = piles.length;
        int[][] dp = new int[length][length];
        for (int i = 0; i < length; i++) {
            dp[i][i] = piles[i];
        }
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[i][j] = Math.max(piles[i] - dp[i + 1][j], piles[j] - dp[i][j - 1]);
            }
        }
        return dp[0][length - 1] > 0;
    }

    public boolean stoneGameIdp(int[] piles) {
        int length = piles.length;
        int[] dp = new int[length];
        System.arraycopy(piles, 0, dp, 0, length);
        for (int i = length - 2; i >= 0; i--) {
            for (int j = i + 1; j < length; j++) {
                dp[j] = Math.max(piles[i] - dp[j], piles[j] - dp[j - 1]);
            }
        }
        return dp[length - 1] > 0;
    }

    public boolean stoneGameI(int[] piles) {
        return true;
    }

    public int stoneGameII(int[] piles) {
        int n = piles.length;
        int[] sum = new int[n];
        for(int i = n - 1; i >= 0; i--){
            if(i == n - 1) sum[i] = piles[i];
            else sum[i] = sum[i + 1] + piles[i];
        }
        int[][] memo = new int[n][n];
        return helper(piles, memo, sum, 0, 1);
    }
    private int helper(int[] piles, int[][] memo, int[] sum, int index, int M){
        if(index == piles.length) return 0;
        if(piles.length - index <= 2*M) return sum[index];
        if(memo[index][M] != 0) return memo[index][M];
        int min = Integer.MAX_VALUE;
        for(int i = 1; i <= 2 * M; i++){
            min = Math.min(min, helper(piles, memo, sum, index + i, Math.max(i, M)));
        }
        return memo[index][M] = sum[index] - min;
    }

    public String stoneGameIII(int[] stoneValue) {
        int n = stoneValue.length;
        int[] suffixSum = new int[n];
        suffixSum[n - 1] = stoneValue[n - 1];
        for (int i = n - 2; i >= 0; --i) {
            suffixSum[i] = suffixSum[i + 1] + stoneValue[i];
        }
        int[] f = new int[n + 1];
        // 边界情况，当没有石子时，分数为 0
        // 为了代码的可读性，显式声明
        f[n] = 0;
        for (int i = n - 1; i >= 0; --i) {
            int bestj = f[i + 1];
            for (int j = i + 2; j <= i + 3 && j <= n; ++j) {
                bestj = Math.min(bestj, f[j]);
            }
            f[i] = suffixSum[i] - bestj;
        }
        int total = 0;
        for (int value : stoneValue) {
            total += value;
        }
        if (f[0] * 2 == total) {
            return "Tie";
        } else {
            return f[0] * 2 > total ? "Alice" : "Bob";
        }
    }

    public boolean winnerSquareGame(int n) {
        boolean[] f = new boolean[n + 1];
        for (int i = 1; i <= n; ++i) {
            for (int k = 1, k2 = k * k; k2 <= Math.sqrt(i); ++k) {
                if (!f[i - k2]) {
                    f[i] = true;
                    break;
                }
            }
        }
        return f[n];
    }

}
