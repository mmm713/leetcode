package com.home.learn.google;

public class MaximumDistanceElectricalCar {
    // 有个电车，然后有最大容量为K的电池，每天你可以选择呆在原地回复一个unit，或者消耗一个unit开车，
    // 每天开车的距离会不同，现在就是给定电池容量K，以及每天可以开的距离[d1, d2, d3...]，求哪些天move可以使得电车走的最远

    //dp[i][j] 代表剩余 j 个电量情况下，第 i 天结束时可以达到最远的距离
    //dis = {3, 5, 6, 8, 9, 3, 4, 7, 3, 2, 8}, k = 3
    //当天结束剩 i 个电，等于之前i - 1没走，或者i + 1个电跑掉自己
    //dp[i][j] = max(dp[i - 1][j - 1], dp[i + 1][j - 1] + dis[j])
    //注意保证i +/- 1在合法范围
    //{1,1,1,9,9,9}, k = 3，跑法为 休，跑，休，跑跑跑
    public int maxDistance(int[] dis, int k) {
        int[][] dp = new int[dis.length][k + 1];
        dp[0][k - 1] = dis[0];
        int res = dis[0];
        for (int i = 1; i < dis.length; i++) {
            for (int j = Math.max(0, k - i - 1); j <= k; j++) {
                if(j == 0) {
                    dp[i][j] = dp[i - 1][j + 1] + dis[i];
                } else if(j == k) {
                    dp[i][j] = dp[i - 1][j - 1];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j - 1], dp[i - 1][j + 1] + dis[i]);
                }
                res = Math.max(res, dp[i][j]);
            }
        }
        return res;
    }
}
