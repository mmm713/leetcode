package com.home.learn.leetcode.dp;

public class BackpackProblem {
    /**
     * 0-1背包问题
     * @param V	背包容量
     * @param N	物品种类
     * @param weight 物品重量
     * @param value	物品价值
     * @return 返回装入物品下标
     * dp[i][j]表示前 i 件物品能装入容量为j的背包中的物品价值总和的最大值
     */
    public String ZeroOnePack(int V, int N, int[] weight,int[] value){
        //初始化动态规划数组
        int[][] dp = new int[N + 1][V + 1];
        //为了便于理解,将dp[i][0]和dp[0][j]均置为0，从1开始计算
        for(int i = 1; i < N + 1; i++){
            for(int j = 1; j < V + 1; j++){
                //如果第i件物品的重量大于背包容量j,则不装入背包
                //由于weight和value数组下标都是从0开始,故注意第i个物品的重量为weight[i-1],价值为value[i-1]
                if(weight[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j];
                } else {
                    dp[i][j] = Math.max(dp[i - 1][j], dp[i - 1][j - weight[i - 1]] + value[i - 1]);
                }
            }
        }
        //逆推找出装入背包的所有商品的编号
        int j = V;
        StringBuilder numStr = new StringBuilder();
        for(int i = N; i > 0; i--){
            //若果dp[i][j]>dp[i-1][j],这说明第i件物品是放入背包的
            if(dp[i][j] > dp[i - 1][j]){
                numStr.insert(0, i + " ");
                j = j - weight[i - 1];
            }
            if(j == 0)
                break;
        }
        return numStr.toString();
    }
}
