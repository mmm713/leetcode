package com.home.learn.facebook;

public class PowerTwoNumber {
    //快速pow算法。复杂度logn，中途一旦出现奇数次幂，多乘一次x即可
    public double myPow(double x, int n) {
        double ans = 1.0;
        long absN = Math.abs((long)n);
        while(absN > 0) {
            if(absN % 2 == 1) ans *= x;
            absN /= 2.0;
            x *= x;
        }
        return n < 0 ?  1/ans : ans;
    }
}
