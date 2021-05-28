package com.home.learn.bytedance;

import java.util.Arrays;

public class TallestBillboard {
    //dp[i][j] 表示从前i个数字中选出数字组成两组（组0和组1，这里假设组0数字之和一定小于组1），
    // 此时的二者中的较大长度，其实也就是组1的数字之和，并且j表示二组数字之和的差值。
    // 接下来就是要找出状态转移方程，如何从之前的状态推导出 dp[i][j]。当把 rod[i] 加入其中的一组时，
    // 此时有三种情况：
    //
    //将 rod[i] 加入组1时，由于组1的数字和大，所以增加新数字会拉大两组原本的差值，
    // 若加入之后的差值为j，则加入之前则为 j-rods[i]，
    // 所以可以用 dp[i-1][j-rods[i]] + rods[i] 来更新 dp[i][j]。
    //
    //将将 rod[i] 加入组0时，且加入之后组0的数字之和仍小于组1，
    // 但此时二者的差距变小了，若加入之后的差值为j，则加入之前则为 j+rods[i]，
    // 所以可以用 dp[i-1][j+rods[i]] 来更新 dp[i][j]。
    //
    //将将 rod[i] 加入组0时，且加入之后组0的数字之和超过了组1，
    // 说明这个新数字要大于原本两个组之间的差值，若加入之后的差值为j，则加入之前则为 rods[i]-j，
    // 所以可以用 dp[i-1][rods[i]-j] + j 来更新 dp[i][j]。
    //
    //搞清楚了这三种情况，就可以写出代码了，最终的结果是存在 dp[n][0] 中的，
    // 因为这表示从前n个数字中选出数字组成两组，且两组之和差为0，说明可以组成相同和的两组
    public int tallestBillboard(int[] rods) {
        int sum = 0;
        for(int rod : rods) sum += rod;
        int[] dp = new int[sum + 1];
        Arrays.fill(dp, -1);
        dp[0] = 0;
        for(int rod : rods){
            int[] pre = dp.clone();
            for(int j=0; j<=sum-rod; j++){
                if(pre[j] != -1){
                    dp[j+rod] = Math.max(dp[j+rod], pre[j]);
                    dp[Math.abs(j-rod)] = Math.max(dp[Math.abs(j-rod)], pre[j]+Math.min(rod, j));
                }
            }
        }
        return dp[0];
    }
}
