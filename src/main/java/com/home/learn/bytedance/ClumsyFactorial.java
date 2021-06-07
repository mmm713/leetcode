package com.home.learn.bytedance;

public class ClumsyFactorial {
    //数字按照乘除加减顺序，直接4个一组递归求解即可
    public int clumsy(int n) {
        if (n <= 3) return helper(n);
        int res = n * (n - 1) / (n - 2) + (n - 3);
        n -= 4;
        while (n >= 4) {
            res -= (n * (n - 1) / (n - 2) - (n - 3));
            n -= 4;
        }
        return res - helper(n);
    }

    private int helper(int n) {
        switch(n) {
            case 0:
                return 0;
            case 1:
                return 1;
            case 2:
                return 2;
            case 3:
                return 6;
            default:
                return -1;
        }
    }
}
