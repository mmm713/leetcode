package com.home.learn.microsoft;

public class StoneGame {
    public boolean stoneGame(int[] piles) {
        int N = piles.length;
        // dp[i + 1][j + 1] = the value of the game [piles[i], ..., piles[j]].
        int[][] dp = new int[N+2][N+2];
        for (int size = 1; size <= N; ++size)
            for (int i = 0; i + size <= N; ++i) {
                int j = i + size - 1;
                int parity = (j + i + N) % 2;  // j - i - N; but +x = -x (mod 2)
                if (parity == 1)
                    dp[i+1][j+1] = Math.max(piles[i] + dp[i+2][j+1], piles[j] + dp[i+1][j]);
                else
                    dp[i+1][j+1] = Math.min(-piles[i] + dp[i+2][j+1], -piles[j] + dp[i+1][j]);
            }
        return dp[1][N] > 0;
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
}
