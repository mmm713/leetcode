package com.home.learn.bytedance;

import java.util.Arrays;

public class BestTimeBuySellStock {
    /*
    与其余的股票问题类似，我们使用一系列变量存储「买入」的状态，再用一系列变量存储「卖出」的状态，通过动态规划的方法即可解决本题。

我们用 buy[i][j] 表示对于数组 {prices}[0..i]prices[0..i] 中的价格而言，进行恰好 jj 笔交易，并且当前手上持有一支股票，这种情况下的最大利润；用 {sell}[i][j]sell[i][j] 表示恰好进行 jj 笔交易，并且当前手上不持有股票，这种情况下的最大利润。

那么我们可以对状态转移方程进行推导。对于 buy[i][j]，我们考虑当前手上持有的股票是否是在第 ii 天买入的。如果是第 ii 天买入的，那么在第 i-1i−1 天时，我们手上不持有股票，对应状态 sell[i−1][j]，并且需要扣除 {prices}[i]prices[i] 的买入花费；如果不是第 ii 天买入的，那么在第 i-1i−1 天时，我们手上持有股票，对应状态 {buy}[i-1][j]buy[i−1][j]。那么我们可以得到状态转移方程：

{buy}[i][j] = \max \big\{ {buy}[i-1][j], {sell}[i-1][j] - {price}[i] \big\}
buy[i][j]=max{buy[i−1][j],sell[i−1][j]−price[i]}

同理对于 {sell}[i][j]sell[i][j]，如果是第 ii 天卖出的，那么在第 i-1i−1 天时，我们手上持有股票，对应状态 {buy}[i-1][j-1]buy[i−1][j−1]，并且需要增加 {prices}[i]prices[i] 的卖出收益；如果不是第 ii 天卖出的，那么在第 i-1i−1 天时，我们手上不持有股票，对应状态 sell[i−1][j]。那么我们可以得到状态转移方程：

{sell}[i][j] = \max \big\{ {sell}[i-1][j], {buy}[i-1][j-1] + {price}[i] \big\}
sell[i][j]=max{sell[i−1][j],buy[i−1][j−1]+price[i]}

由于在所有的 nn 天结束后，手上不持有股票对应的最大利润一定是严格由于手上持有股票对应的最大利润的，然而完成的交易数并不是越多越好（例如数组 {prices}prices 单调递减，我们不进行任何交易才是最优的），因此最终的答案即为 {sell}[n-1][0..k]sell[n−1][0..k] 中的最大值。

细节

在上述的状态转移方程中，确定边界条件是非常重要的步骤。我们可以考虑将所有的 {buy}[0][0..k]buy[0][0..k] 以及 {sell}[0][0..k]sell[0][0..k] 设置为边界。

对于 {buy}[0][0..k]buy[0][0..k]，由于只有 {prices}[0]prices[0] 唯一的股价，因此我们不可能进行过任何交易，那么我们可以将所有的 {buy}[0][1..k]buy[0][1..k] 设置为一个非常小的值，表示不合法的状态。而对于 {buy}[0][0]buy[0][0]，它的值为 -{prices}[0]−prices[0]，即「我们在第 00 天以 {prices}[0]prices[0] 的价格买入股票」是唯一满足手上持有股票的方法。

对于 {sell}[0][0..k]sell[0][0..k]，同理我们可以将所有的 {sell}[0][1..k]sell[0][1..k] 设置为一个非常小的值，表示不合法的状态。而对于 {sell}[0][0]sell[0][0]，它的值为 00，即「我们在第 00 天不做任何事」是唯一满足手上不持有股票的方法。

在设置完边界之后，我们就可以使用二重循环，在 i\in [1,n), j \in [0, k]i∈[1,n),j∈[0,k] 的范围内进行状态转移。需要注意的是，{sell}[i][j]sell[i][j] 的状态转移方程中包含 {buy}[i-1][j-1]buy[i−1][j−1]，在 j=0j=0 时其表示不合法的状态，因此在 j=0j=0 时，我们无需对 {sell}[i][j]sell[i][j] 进行转移，让其保持值为 00 即可。

最后需要注意的是，本题中 kk 的最大值可以达到 10^910
9
 ，然而这是毫无意义的，因为 nn 天最多只能进行 \lfloor \frac{n}{2} \rfloor⌊
2
n
​
 ⌋ 笔交易，其中 \lfloor x \rfloor⌊x⌋ 表示对 xx 向下取整。因此我们可以将 kk 对 \lfloor \frac{n}{2} \rfloor⌊
2
n
​
 ⌋ 取较小值之后再进行动态规划。
     */
    public int maxProfit4(int k, int[] prices) {
        if (prices.length == 0) {
            return 0;
        }

        int n = prices.length;
        k = Math.min(k, n / 2);
        int[] buy = new int[k + 1];
        int[] sell = new int[k + 1];

        buy[0] = -prices[0];
        sell[0] = 0;
        for (int i = 1; i <= k; ++i) {
            buy[i] = sell[i] = Integer.MIN_VALUE / 2;
        }

        for (int i = 1; i < n; ++i) {
            buy[0] = Math.max(buy[0], sell[0] - prices[i]);
            for (int j = 1; j <= k; ++j) {
                buy[j] = Math.max(buy[j], sell[j] - prices[i]);
                sell[j] = Math.max(sell[j], buy[j - 1] + prices[i]);
            }
        }

        return Arrays.stream(sell).max().getAsInt();
    }

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
