package com.home.learn.bytedance;

public class BestTimeBuySellStock {
    //只可以买卖一次
    public int maxProfit(int[] prices) {
        int min = Integer.MAX_VALUE;
        int max = 0;
        for (int price : prices) {
            if (price < min) {
                min = price;
            }
            max = Math.max(max, price - min);
        }
        return max;
    }

    //可以无限次买卖
    /*
    我们定义local[i][j]为在到达第i天时最多可进行j次交易并且最后一次交易在最后一天卖出的最大利润，此为局部最优。
    然后我们定义global[i][j]为在到达第i天时最多可进行j次交易的最大利润，此为全局最优。它们的递推式为：
    local[i][j] = max(global[i - 1][j - 1] + max(diff, 0), local[i - 1][j] + diff)
    global[i][j] = max(local[i][j], global[i - 1][j])
    其中局部最优值是比较前一天并少交易一次的全局最优加上大于0的差值，和前一天的局部最优加上差值中取较大值，而全局最优比较局部最优和前一天的全局最优

    这里我们先解释最多可以进行k次交易的算法，然后最多进行两次我们只需要把k取成2即可。我们还是使用“局部最优和全局最优解法”。
    我们维护两种量，一个是当前到达第i天可以最多进行j次交易，最好的利润是多少（global[i][j]），另一个是当前到达第i天，最多可进行j次交易，
    并且最后一次交易在当天卖出的最好的利润是多少（local[i][j]）。下面我们来看递推式，全局的比较简单，
    global[i][j]=max(local[i][j],global[i-1][j])，
    也就是去当前局部最好的，和过往全局最好的中大的那个（因为最后一次交易如果包含当前天一定在局部最好的里面，否则一定在过往全局最优的里面）。
    对于局部变量的维护，递推式是
    local[i][j]=max(global[i-1][j-1]+max(diff,0),local[i-1][j]+diff)，
    也就是看两个量，第一个是全局到i-1天进行j-1次交易，然后加上今天的交易，如果今天是赚钱的话（也就是前面只要j-1次交易，最后一次交易取当前天），
    第二个量则是取local第i-1天j次交易，然后加上今天的差值（这里因为local[i-1][j]比如包含第i-1天卖出的交易，所以现在变成第i天卖出，
    并不会增加交易次数，而且这里无论diff是不是大于0都一定要加上，因为否则就不满足local[i][j]必须在最后一天卖出的条件了）
     */
    public int maxProfitII(int[] prices) {
        int res = 0;
        for (int i = 1; i < prices.length; i++) {
            res += Math.max(prices[i] - prices[i - 1], 0);
        }
        return res;
    }

    //最多只能买卖2次
    public int maxProfitIII(int[] prices) {
        if (prices.length <= 0) return 0;
        int n = prices.length;
        int[] global = new int[3];
        int[] local = new int[3];
        for (int i = 1; i < n; ++i) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 2; j >= 1; --j) {
                local[j] = Math.max(global[j - 1], local[j]) + diff;
                global[j] = Math.max(local[j], global[j]);
            }
        }
        return global[2];
    }

    //最多只能买卖k次
    public int maxProfitIV(int k, int[] prices) {
        if (prices.length <= 0) return 0;
        if (k >= prices.length) {
            return solve(prices);
        }
        int n = prices.length;
        int[][] global = new int[n][k + 1];
        int[][] local = new int[n][k + 1];
        for (int i = 1; i < n; ++i) {
            int diff = prices[i] - prices[i - 1];
            for (int j = 1; j <= k; ++j) {
                local[i][j] = Math.max(global[i - 1][j - 1], local[i - 1][j])  + diff;
                global[i][j] = Math.max(local[i][j], global[i - 1][j]);
            }
        }
        return global[n - 1][k];
    }

    public int solve(int[] prices) {
        int sum = 0;
        for (int i = 1; i < prices.length; i++) {
            sum += Math.max(prices[i] - prices[i - 1], 0);
        }
        return sum;
    }
}
