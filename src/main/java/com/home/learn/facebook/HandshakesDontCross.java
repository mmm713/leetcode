package com.home.learn.facebook;

public class HandshakesDontCross {
    public int numberOfWays(int num_people) {
        int[] res = new int[num_people / 2 + 1];
        res[0] = 1;
        res[1] = 1;
        return (int)dfs(num_people / 2, res);
    }

    private long dfs(int n, int[] res) {
        if(res[n] > 0) {
            return res[n];
        }
        long ret = 0;
        for(int i = 1; i <= n ; i++) {
            ret += (dfs(i - 1, res) * dfs(n - i, res)) % 1000000007;
        }
        res[n] = (int)(ret % 1000000007);
        return res[n];
    }
}
