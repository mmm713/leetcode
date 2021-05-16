package com.home.learn.facebook;

public class FindKthBinaryNumber {
    public int findKthBinary(int n, int k) {
        int r = 1;
        int comb = combination(n, r);
        int sum = 0;
        while(k - comb > 0) {
            sum += comb;
            r++;
            comb = combination(n, r);
        }
        int idx = k - sum;
        int[] ele = new int[n];
        for(int i = 0; i < n; i++) {
            ele[i] = i << n;
        }
        return findIdx(ele, idx, r);
    }

    private int findIdx(int[] ele, int idx, int r) {
        int res = 0;
        //排列组合确定位置
        return res;
    }

    private int combination(int n, int r) {
        return factorial(n) / (factorial(r) * factorial(n-r));
    }

    private int factorial(int n) {
        int fact = 1;
        int i = 1;
        while(i <= n) {
            fact *= i;
            i++;
        }
        return fact;
    }
}
