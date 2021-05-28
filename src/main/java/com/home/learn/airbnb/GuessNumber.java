package com.home.learn.airbnb;

public class GuessNumber {
    public int guessNumber(int n) {
        int low = 1;
        int high = n;
        while (low <= high) {
            int mid = low + (high - low) / 2;
            int res = guess(mid);
            if (res == 0) {
                return mid;
            } else if (res < 0) {
                high = mid - 1;
            } else {
                low = mid + 1;
            }
        }
        return -1;
    }
    private int guess(int i) {
        return i;
    }

    /**
        此题是之前那道 Guess Number Higher or Lower 的拓展，难度增加了不少，根据题目中的提示，这道题需要用到 Minimax 极小化极大算法，
        关于这个算法可以参见这篇讲解，并且题目中还说明了要用 DP 来做，需要建立一个二维的 dp 数组，其中 dp[i][j] 表示从数字i到j之间猜中任意一个数字最少需要花费的钱数，
        那么需要遍历每一段区间 [j, i]，维护一个全局最小值 global_min 变量，然后遍历该区间中的每一个数字，计算局部最大值 local_max = k + max(dp[j][k - 1], dp[k + 1][i])，
        这个正好是将该区间在每一个位置都分为两段，然后取当前位置的花费加上左右两段中较大的花费之和为局部最大值，为啥要取两者之间的较大值呢，
        因为要 cover 所有的情况，就得取最坏的情况。然后更新全局最小值，最后在更新 dp[j][i] 的时候看j和i是否是相邻的，相邻的话赋为j，
        否则赋为 global_min。这里为啥又要取较小值呢，因为 dp 数组是求的 [j, i] 范围中的最低 cost，比如只有两个数字1和2，那么肯定是猜1的 cost 低，
        是不有点晕，没关系，博主继续来绕你。如果只有一个数字，那么不用猜，cost 为0。如果有两个数字，比如1和2，猜1，即使不对，cost 也比猜2要低。
        如果有三个数字 1，2，3，那么就先猜2，根据对方的反馈，就可以确定正确的数字，所以 cost 最低为2。如果有四个数字 1，2，3，4，那么情况就有点复杂了，
        策略是用k来遍历所有的数字，然后再根据k分成的左右两个区间，取其中的较大 cost 加上k。
        当k为1时，左区间为空，所以 cost 为0，而右区间 2，3，4，根据之前的分析应该取3，所以整个 cost 就是 1+3=4。
        当k为2时，左区间为1，cost 为0，右区间为 3，4，cost 为3，整个 cost 就是 2+3=5。
        当k为3时，左区间为 1，2，cost 为1，右区间为4，cost 为0，整个 cost 就是 3+1=4。
        当k为4时，左区间 1，2，3，cost 为2，右区间为空，cost 为0，整个 cost 就是 4+2=6。
        综上k的所有情况，此时应该取整体 cost 最小的，即4，为最后的答案，这就是极小化极大算法，参见代码如
     */
    public int getMoneyAmount(int n) {
        int[][] dp = new int[n + 1][n + 1];
        for (int len = 2; len <= n; len++) {
            for (int start = 1; start <= n - len + 1; start++) {
                int minres = Integer.MAX_VALUE;
                for (int piv = start; piv < start + len - 1; piv++) {
                    int res = piv + Math.max(dp[start][piv - 1], dp[piv + 1][start + len - 1]);
                    minres = Math.min(res, minres);
                }
                dp[start][start + len - 1] = minres;
            }
        }
        return dp[1][n];
    }

    //老游戏， A B表示数字和位置正确
    public String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] counts = new int[10];
        for(int i = 0; i < secret.length(); i++) {
            if(secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                if(counts[secret.charAt(i) - '0']++ < 0) {
                    cows++;
                }
                if(counts[guess.charAt(i) - '0']-- > 0) {
                    cows++;
                }
            }
        }
        return String.valueOf(bulls) + 'A' + cows + 'B';
    }
}
