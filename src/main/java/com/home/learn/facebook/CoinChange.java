package com.home.learn.facebook;

import java.util.Arrays;

public class CoinChange {
    //各种硬币求最少到amount
    public int coinChange(int[] coins, int amount) {
        int[] cache = new int[amount + 1];
        Arrays.fill(cache, amount + 1);
        cache[0] = 0;
        for (int coin : coins) {
            for (int value = coin; value <= amount; value++) {
                cache[value] = Math.min(cache[value], cache[value - coin] + 1);
            }
        }
        return cache[amount] <= amount ? cache[amount] : -1;
    }

    //有多少种组合
    public int change(int amount, int[] coins) {
        int[] dp = new int[++amount];
        dp[0] = 1;
        for (int coin : coins) {
            for (int x = coin; x < amount; x++) {
                dp[x] += dp[x - coin];
            }
        }
        return dp[--amount];
    }
}
